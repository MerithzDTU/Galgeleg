package dk.dtu.merithz.galgeleg.business;

import java.util.ArrayList;

public interface ISpilLogik {
    ArrayList<String> getBrugteBogstaver();
    ArrayList<String> getForkerteBogstaver();
    String getSynligtOrd();
    String getOrdet();
    int getAntalForkerteBogstaver();
    int getAntalBrugteBogstaver();
    boolean erSidsteBogstavKorrekt();
    boolean erSpilletVundet();
    boolean erSpilletTabt();
    boolean erSpilletSlut();
    void opdaterSynligtOrd();
    boolean gætBogstav(String bogstav);
}
