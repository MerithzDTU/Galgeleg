package dk.dtu.merithz.galgeleg.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class HighscoreDTO {
    private String brugernavn;
    private int score;
    private Date timestamp;
    private String ordet;

    public HighscoreDTO(String brugernavn, int score, Date timestamp, String ordet){
        this.brugernavn = brugernavn;
        this.score = score;
        //Tilføj timestamp og ordet
        this.timestamp = timestamp;
        this.ordet = ordet;

    }

    //Factory-method til at pumpe json objekter ud.
    public static HighscoreDTO fromJSON(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        String navn = jsonObj.getString("navn");
        long timestamp = jsonObj.getLong("timestamp");
        int score = jsonObj.getInt("score");
        Date date = new Date(timestamp);
        String ordet = jsonObj.getString("ordet");

        return new HighscoreDTO(navn,score,date,ordet);
    }

    public String toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("navn",brugernavn);
        long tid = timestamp.getTime();
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

    @Override
    public String toString() {
        return "Highscore{" +
                "brugernavn='" + brugernavn + '\'' +
                ", score=" + score +
                '}';
    }
}
