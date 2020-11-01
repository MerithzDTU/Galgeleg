package dk.dtu.merithz.galgeleg.business;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.merithz.galgeleg.data.HentOrd;

public class SpilLogik implements ISpilLogik {
    private String ordet;
    private int antalMuligeFejl;
    private ArrayList<String> brugteBogstaver;
    private ArrayList<String> forkerteBogstaver;
    private String synligtOrd;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;


    public SpilLogik(String ordet, int antalMuligeFejl) {
        this.ordet = ordet;
        this.antalMuligeFejl = antalMuligeFejl;
        brugteBogstaver = new ArrayList<>();
        forkerteBogstaver = new ArrayList<>();
        opdaterSynligtOrd();
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
    public void gætBogstav(String bogstav) {
        if (bogstav.length() != 1) return;
        System.out.println("Der gættes på bogstavet: " + bogstav);
        if (brugteBogstaver.contains(bogstav)) return;
        if (spilletErVundet || spilletErTabt) return;

        brugteBogstaver.add(bogstav);

        if (ordet.contains(bogstav)) {
            sidsteBogstavVarKorrekt = true;
            System.out.println("Bogstavet var korrekt: " + bogstav);
        } else {
            // Vi gættede på et bogstav der ikke var i ordet.
            sidsteBogstavVarKorrekt = false;
            System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
            forkerteBogstaver.add(bogstav);
            if (getAntalForkerteBogstaver() > antalMuligeFejl) {
                spilletErTabt = true;
            }
        }
        opdaterSynligtOrd();
    }
}
