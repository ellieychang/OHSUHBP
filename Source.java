public class Source {
    private String label;
    private String url;
    public Source(String label, String url) {
        this.label = label;
        this.url = url;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public String toString() {
        return "[label = " + label + "; url = " + url + "]";
    }

    public String getLabel() {
        return label;
    }
}
