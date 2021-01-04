package dk.dtu.merithz.galgeleg.business;

import android.app.Activity;

import org.json.JSONException;

import java.util.Date;
import java.util.List;
import java.util.Random;

import dk.dtu.merithz.galgeleg.data.HentOrd;
import dk.dtu.merithz.galgeleg.data.HighscoreDTO;
import dk.dtu.merithz.galgeleg.data.HighscoreSaver;
import dk.dtu.merithz.galgeleg.data.Sværhedsgrad;

public class SpilHandler implements ISpilHandler {
    private static SpilHandler instance = null;
    private HentOrd hentOrd;
    private HighscoreSaver highscoreSaver;
    private SpilLogik spilLogik;
    private List<String> muligeord;
    private Sværhedsgrad sværhedsgrad;
    private String aktueltBrugerNavn = "";


    //Konstruktør lavet private, så klassen ikke kan blive instantieret
    private SpilHandler(HentOrd hentOrd) {
        this.hentOrd = hentOrd;
    }

    //Singleton, hvis der ingen instans findes af klassen, så laves den her.
    public static SpilHandler getInstance(){
        if(instance == null){
            instance = new SpilHandler(new HentOrd());
        }
        return instance;
    }

    public String getAktueltBrugerNavn(){
        return aktueltBrugerNavn;
    }

    public void setAktueltBrugerNavn(String aktueltBrugerNavn){
        this.aktueltBrugerNavn = aktueltBrugerNavn;
    }

    public void gemHighscore() throws JSONException {
        //Tilføj timestamp og ordet
        Date dato = new Date();
        int antalForsøg = spilLogik.getAntalBrugteBogstaver();
        int antalForkert = spilLogik.getAntalForkerteBogstaver();
        String ordet = spilLogik.getOrdet();
        HighscoreDTO highscore = new HighscoreDTO(aktueltBrugerNavn,dato,ordet,antalForsøg,antalForkert,sværhedsgrad);
        highscoreSaver.gem(highscore);
    }

    public SpilLogik getSpilLogik(){
        return spilLogik;
    }

    @Override
    public final void initSpil(Sværhedsgrad sværhedsgrad, Activity activity) {
        highscoreSaver = HighscoreSaver.fromActivity(activity);
        try{
            muligeord = hentOrd.hentOrdFraRegneark(String.valueOf(sværhedsgrad.getValue()));
            this.sværhedsgrad = sværhedsgrad;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public final void startSpil() {
        String ordet = muligeord.get(new Random().nextInt(muligeord.size()));
        spilLogik = new SpilLogik(ordet);
    }

    @Override
    public void slutSpil() {
        try {
            gemHighscore();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
