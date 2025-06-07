package ir.ac.kntu;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public abstract class Notification {
    private DeclarationType declarationType;
    private String abute;
    private LocalDate crDate;
    private LocalTime crTime;
    private boolean seen = false;
    private boolean canSeeOrNot = false;

    public boolean isCanSeeOrNot() {
        return canSeeOrNot;
    }

    public void setCanSeeOrNot(boolean canSeeOrNot) {
        this.canSeeOrNot = canSeeOrNot;
    }

    public DeclarationType getDeclarationType() {
        return declarationType;
    }

    public void setDeclarationType(DeclarationType declarationType) {
        this.declarationType = declarationType;
    }

    public String getAbute() {
        return abute;
    }

    public void setAbute(String abute) {
        this.abute = abute;
    }

    public Notification(DeclarationType declarationType, String abute) {
        this.declarationType = declarationType;
        this.abute = abute;
        this.crDate = LocalDate.now();
        this.crTime = LocalTime.now();
    }

    public LocalDate getCrDate() {
        return crDate;
    }

    public void setCrDate(LocalDate crDate) {
        this.crDate = crDate;
    }

    public LocalTime getCrTime() {
        return crTime;
    }

    public void setCrTime(LocalTime crTime) {
        this.crTime = crTime;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public void interNotif(Customer customer, Scanner scanner) {
        System.out.println("interNotif");
    }

    public void setTimeNow() {
        crDate = LocalDate.now();
        crTime = LocalTime.now();
    }

    @Override
    public String toString() {
        return "Notification [declarationType=" + declarationType + ", abute=" + abute + ", crDate=" + crDate
                + ", crTime=" + crTime + "]";
    }
}
