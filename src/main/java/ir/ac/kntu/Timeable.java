package ir.ac.kntu;

import java.time.LocalDateTime;

public abstract class Timeable {
    private String trans = "bebedibabidibo";
    private LocalDateTime localDateTime;

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
