package dk.dtu.merithz.galgeleg.business;

import java.util.ArrayList;
import java.util.Observable;
import static dk.dtu.merithz.galgeleg.business.SpilLogikData.SpilLogikStatus;

public class SpilLogik extends Observable implements ISpilLogik{
    private final int antalMuligeFejl = 6;
    private String ordet;
    private ArrayList<String> brugteBogstaver;
    private ArrayList<String> forkerteBogstaver;
    private String synligtOrd;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    protected SpilLogikData data = new SpilLogikData();

    public SpilLogik(String ordet) {
        this.ordet = ordet;
        data.setStatus(SpilLogikStatus.ord);
        data.setOrdet(ordet);
        setChanged();
        notifyObservers(data);
        brugteBogstaver = new ArrayList<>();
        forkerteBogstaver = new ArrayList<>();
        opdaterSynligtOrd();
        logStatus();
    }

    private void checkVundetSpil(){
        spilletErVundet = true;
        for (int n = 0; n < ordet.length(); n++){
            String c = ordet.substring(n, n + 1);
            if (!brugteBogstaver.contains(c)){
                spilletErVundet = false;
                break;
            }
        }
        if (spilletErVundet) {
            data.setStatus(SpilLogikStatus.vundet);
            data.setAntalForsøg(String.valueOf(brugteBogstaver.size()));
            setChanged();
            notifyObservers(data);
            SpilHandler.getInstance().gemHighscore(getAntalBrugteBogstaver());
        }
    }

    private void checkTabtSpil(){
        if (getAntalForkerteBogstaver() >= antalMuligeFejl) {
            spilletErTabt = true;
            data.setStatus(SpilLogikStatus.tabt);
            setChanged();
            notifyObservers(data);
            System.out.println("Her ved checktabtspil");
        }
    }


    @Override
    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    @Override
    public ArrayList<String> getForkerteBogstaver() {
        return forkerteBogstaver;
    }

    @Override
    public String getSynligtOrd() {
        return synligtOrd;
    }

    @Override
    public String getOrdet() {
        return ordet;
    }

    @Override
    public int getAntalForkerteBogstaver() {
        return forkerteBogstaver.size();
    }

    @Override
    public int getAntalBrugteBogstaver(){return brugteBogstaver.size();}

    @Override
    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
    }

    @Override
    public boolean erSpilletVundet() {
        return spilletErVundet;
    }

    @Override
    public boolean erSpilletTabt() {
        return spilletErTabt;
    }

    @Override
    public boolean erSpilletSlut() {
        return spilletErTabt || spilletErVundet;
    }

    @Override
    public void opdaterSynligtOrd() {
        synligtOrd = "";
        for (int n = 0; n < ordet.length(); n++) {
            String bogstav = ordet.substring(n, n + 1);
            if (brugteBogstaver.contains(bogstav)) {
                synligtOrd = synligtOrd + bogstav;
            } else {
                synligtOrd = synligtOrd + "*";
            }
        }
    }

    @Override
    public boolean gætBogstav(String b) {
        if (b.length() != 1) return false;
        String bogstav = b.toLowerCase();
        System.out.println("Der gættes på bogstavet: " + bogstav);
        if (brugteBogstaver.contains(bogstav)) return false;
        if (spilletErVundet || spilletErTabt) return false;

        brugteBogstaver.add(bogstav);
        if (ordet.contains(bogstav)) {
            sidsteBogstavVarKorrekt = true;
            System.out.println("Bogstavet var korrekt: " + bogstav);
            checkVundetSpil();
        } else {
            // Vi gættede på et bogstav der ikke var i ordet.
            sidsteBogstavVarKorrekt = false;
            System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
            forkerteBogstaver.add(bogstav);
            checkTabtSpil();
        }

        opdaterSynligtOrd();
        return sidsteBogstavVarKorrekt;
    }

    public void logStatus() {
        System.out.println("---------- ");
        System.out.println("- ordet (skjult) = " + ordet);
        System.out.println("- synligtOrd = " + synligtOrd);
        System.out.println("- antalForkerteBogstaver = " + getAntalForkerteBogstaver());
        System.out.println("- forkerteBogstaver = " + forkerteBogstaver);
        System.out.println("- brugeBogstaver = " + brugteBogstaver);
        if (spilletErTabt) System.out.println("- SPILLET ER TABT");
        if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
        System.out.println("---------- ");
    }
}
