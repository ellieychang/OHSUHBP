import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Recommendations {

    public static void main(String args[]) throws IOException {
        List<CardInfo> recommendations = null;

        String file = "src/response-Smoker.json";
        // String file = args[0];
        String path = String.valueOf(Paths.get(file));

        JsonReader reader = new JsonReader(new FileReader(path));
        reader.beginObject();
        String name = reader.nextName();
        if (name.equals("cards")) {
            // reads in json file
            recommendations = readCardsArray(reader);
        }
        reader.endObject();
        reader.close();

        // writes html for json file
        Html html = new Html(file);
        html.write(recommendations);

        System.out.println(recommendations);
    }

    public static List<CardInfo> readCardsArray(JsonReader reader) throws IOException {
        // creates arraylist for each recommendation
        List<CardInfo> cards = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            cards.add(readCard(reader));
        }
        reader.endArray();
        return cards;
    }

    public static CardInfo readCard(JsonReader reader) throws IOException {
        String summary = null;
        String indicator = null;
        String detail = null;
        Source source = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("summary")) {
                summary = reader.nextString();
            } else if (name.equals("indicator")) {
                indicator = reader.nextString();
            } else if (name.equals("detail")) {
                detail = reader.nextString();
            } else if (name.equals("source")) {
                source = readSource(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new CardInfo(summary, indicator, detail, source);
    }

    public static Source readSource(JsonReader reader) throws IOException {
        String label = null;
        String url = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("label")) {
                label = reader.nextString();
            } else if (name.equals("url")) {
                url = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Source(label, url);
    }
}
