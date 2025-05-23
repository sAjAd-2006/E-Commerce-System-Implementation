package ir.ac.kntu;

public class Reportage {
    private Report reportTopic;
    private String text;
    private Check check;
    private String answer;

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

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
        this.check = Check.Registered;
        this.answer = "No answer";
    }

    public Reportage() {
        this.check = Check.Registered;
        this.answer = "No answer";
    }

    @Override
    public String toString() {
        return ("ReportTopic: " + getReportTopic() + " Text: " + getText());
    }
}
