package ir.ac.kntu;

public class Reportage {
    private Report reportTopic;
    private String text;
    private Check check;

    public Report getReportTopic() {
        return reportTopic;
    }

    public void setReportTopic(Report reportTopic) {
        this.reportTopic = reportTopic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Reportage(Report reportTopic, String text) {
        this.reportTopic = reportTopic;
        this.text = text;
    }

    public Reportage() {
    }

    public Check getCeck() {
        return check;
    }

    public void setCeck(Check check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return ("ReportTopic: " + getReportTopic() + " Text: " + getText());
    }
}
