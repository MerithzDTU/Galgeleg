package dk.dtu.merithz.galgeleg.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dk.dtu.merithz.galgeleg.data.HentOrd;

public class SpilOpstarter implements ISpilOpstarter {
    private static SpilOpstarter instance = null;
    private HentOrd hentOrd;

    private List<String> muligeord;
    private int antalMuligeFejl;
    private String aktueltBrugerNavn = "";

    SpilLogik spilLogik;



    public static SpilOpstarter getInstance(){
        if(instance == null){
            instance = new SpilOpstarter(new HentOrd());
        }
        return instance;
    }

    public SpilOpstarter(HentOrd hentOrd) {
        this.hentOrd = hentOrd;
    }

    public String getAktueltBrugerNavn(){
        return aktueltBrugerNavn;
    }

    public void setAktueltBrugerNavn(String aktueltBrugerNavn){
        this.aktueltBrugerNavn = aktueltBrugerNavn;
    }

    public SpilLogik getSpilLogik(){
        return spilLogik;
    }

    @Override
    public void initSpil(String sværhedsgrad) {
        if (sværhedsgrad.contains("3")){
            antalMuligeFejl = 6;
        } else if (sværhedsgrad.contains("2")){
            antalMuligeFejl = 5;
        } else{
            antalMuligeFejl = 4;
        }
        try{
            //muligeord = hentOrd.hentOrdFraRegneark(sværhedsgrad);
            muligeord = hentOrd.hentFakeOrd();
            System.out.println("Her er vi nu, ved muligeord");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void startSpil() {
        String ordet = muligeord.get(new Random().nextInt(muligeord.size()));
        spilLogik = new SpilLogik(ordet,antalMuligeFejl);
    }

    @Override
    public void genstartSpil() {

    }

}
