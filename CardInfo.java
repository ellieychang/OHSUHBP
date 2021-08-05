import java.util.List;

public class CardInfo {
    private String summary;
    private String indicator;
    private String detail;
    private Source source;

    public CardInfo(String summary, String indicator, String detail, Source source) {
        this.summary = summary;
        this.indicator = indicator;
        this.detail = detail;
        this.source = source;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getIndicator() {
        return indicator;
    }
    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public Source getSource() {
        return source;
    }
    public void setSource(Source source) {
        this.source = source;
    }
    public String toString() {
        return "Card [summary = " + summary + "\n indicator = " + indicator + "\n detail = " + detail + "\n source = " +
                source + "]\n";
    }
}
