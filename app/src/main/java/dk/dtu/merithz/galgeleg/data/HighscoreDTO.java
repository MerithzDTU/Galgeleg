package dk.dtu.merithz.galgeleg.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class HighscoreDTO {
    private String brugernavn;
    private int score;
    private Date timestamp;
    private String ordet;
    private int antalForsøg;
    private int antalForkert;
    private Sværhedsgrad sværhedsgrad;

    public HighscoreDTO(String brugernavn, Date timestamp, String ordet, int antalForsøg, int antalForkert, Sværhedsgrad sværhedsgrad){
        this.brugernavn = brugernavn;
        //Tilføj timestamp og ordet
        this.timestamp = timestamp;
        this.ordet = ordet;
        this.antalForsøg = antalForsøg;
        this.antalForkert = antalForkert;
        this.sværhedsgrad = sværhedsgrad;

        //beregning af score
        score = (int) (((ordet.length()/antalForsøg) + (1.0 / ordet.length())) * 100) * sværhedsgrad.getValue();
    }

    //Factory-method til at pumpe json objekter ud.
    public static HighscoreDTO fromJSON(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        String navn = jsonObj.getString("navn");
        long timestamp = jsonObj.getLong("timestamp");
        Date date = new Date(timestamp);
        String ordet = jsonObj.getString("ordet");
        int antalForsøg = jsonObj.getInt("antalForsøg");
        int antalForkert = jsonObj.getInt("antalForkert");
        int sværhedsgradIndex = jsonObj.getInt("sværhedsgrad");
        Sværhedsgrad sværhedsgrad = Sværhedsgrad.values()[sværhedsgradIndex];

        return new HighscoreDTO(navn,date,ordet,antalForsøg,antalForkert,sværhedsgrad);
    }

    public String toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("navn",brugernavn);
        long tid = timestamp.getTime();
        jsonObject.put("timestamp",tid);
        jsonObject.put("ordet",ordet);
        jsonObject.put("antalForsøg", antalForsøg);
        jsonObject.put("antalForkert", antalForkert);
        jsonObject.put("sværhedsgrad", sværhedsgrad.ordinal());

        return jsonObject.toString();
    }


    public String getBrugernavn(){
        return brugernavn;
    }

    public int getScore(){
        return score;
    }

    public Date getTimestamp(){return timestamp;}

    public String getOrdet() { return ordet;}

    public int getAntalForsøg() {
        return antalForsøg;
    }

    public int getAntalForkert() {
        return antalForkert;
    }

    public Sværhedsgrad getSværhedsgrad() {
        return sværhedsgrad;
    }

    @Override
    public String toString() {
        return "HighscoreDTO{" +
                "brugernavn='" + brugernavn + '\'' +
                ", score=" + score +
                ", timestamp=" + timestamp +
                ", ordet='" + ordet + '\'' +
                ", antalForsøg=" + antalForsøg +
                ", antalForkert=" + antalForkert +
                '}';
    }
}
