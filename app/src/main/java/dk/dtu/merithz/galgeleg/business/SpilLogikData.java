package dk.dtu.merithz.galgeleg.business;

public class SpilLogikData {
    private SpilLogikStatus status;
    private String ordet;
    private String antalForsøg;

    public enum SpilLogikStatus{
        ord,vundet,tabt
    }

    public SpilLogikStatus getStatus() {
        return status;
    }

    public void setStatus(SpilLogikStatus status) {
        this.status = status;
    }

    public String getOrdet() {
        return ordet;
    }

    public void setOrdet(String ordet) {
        this.ordet = ordet;
    }

    public String getAntalForsøg() {
        return antalForsøg;
    }

    public void setAntalForsøg(String antalForsøg) {
        this.antalForsøg = antalForsøg;
    }

    @Override
    public String toString() {
        return "SpilLogikData{" +
                "status='" + status + '\'' +
                ", ordet='" + ordet + '\'' +
                ", antalForsøg='" + antalForsøg + '\'' +
                '}';
    }
}

