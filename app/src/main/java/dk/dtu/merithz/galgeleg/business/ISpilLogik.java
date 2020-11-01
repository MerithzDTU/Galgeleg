package dk.dtu.merithz.galgeleg.business;

import java.util.ArrayList;

public interface ISpilLogik {
    ArrayList<String> getBrugteBogstaver();
    ArrayList<String> getForkerteBogstaver();
    String getSynligtOrd();
    String getOrdet();
    int getAntalForkerteBogstaver();
    boolean erSidsteBogstavKorrekt();
    boolean erSpilletVundet();
    boolean erSpilletTabt();
    boolean erSpilletSlut();
    void opdaterSynligtOrd();
    void gætBogstav(String bogstav);
}
