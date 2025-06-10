package ir.ac.kntu;

import java.util.Scanner;

public class ReportNotification extends Notification {
    private Reportage reportage;

    public Reportage getReportage() {
        return reportage;
    }

    public void setReportage(Reportage reportage) {
        this.reportage = reportage;
    }

    public ReportNotification(Reportage reportage) {
        super(DeclarationType.Support_request, "Your request status has changed.");
        this.reportage = reportage;
    }

    @Override
    public void interNotif(Customer customer, Scanner scanner) {
        setSeen(true);
        System.out.println("\n" + reportage + " - - - Request status>"
                + reportage.getCheck() + " - - - Support response>" + reportage.getAnswer());
    }
}
