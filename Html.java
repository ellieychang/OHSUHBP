import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Html {
    public String rec;

    public Html(String rec) {
        this.rec = rec.replace(".json", "");
    }

    public String write(List<CardInfo> recommendations) throws IOException {
        // create new html file
        String newFile = rec+".html";

        // read header template html file
        Path headPath = Path.of("src/headerTemplate.txt");
        String htmlHead = Files.readString(headPath);

        // write header to new html file
        BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
        bw.write(htmlHead);
        bw.newLine();

        Path recPath = Path.of("src/recTemplate.txt");

        // replace fields with recommendations
        for (CardInfo card : recommendations) {
            // read recommendation template html file
            String htmlRec = Files.readString(recPath);

            // get recTemplate fields and replace fields in template
            String summary = card.getSummary();
            String indicator = card.getIndicator();
            String detail = card.getDetail();
            Source source = card.getSource();
            if (source.getUrl() != null) {
                System.out.println("true");
                String sourceText = "<a href=\""+source.getUrl()+"\">"+source.getLabel()+"</a>";
                htmlRec = htmlRec.replace("$source", sourceText);
            }
            else {
                System.out.println("false");
                htmlRec = htmlRec.replace("$source", "");
            }
            if (indicator.equals("info")) htmlRec = htmlRec.replace("$color", "#b4ef98");
            if (indicator.equals("warning")) htmlRec = htmlRec.replace("$color", "#ee7a76");
            htmlRec = htmlRec.replace("$summary", summary);
            htmlRec = htmlRec.replace("$detail", detail);

            // write to new html file
            bw.write(htmlRec);
            bw.newLine();
        }
        String close = "</main>";
        bw.write(close);
        bw.close();
        return "done";
    }

    /* public static String readHtml(String htmlFile) throws IOException {
        String line;
        String path = String.valueOf(Paths.get(htmlFile));

        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(ls);
        }
        sb.deleteCharAt(sb.length()-1);
        br.close();
        return sb.toString();
    } */
}

