package dk.dtu.merithz.galgeleg.business;

import org.json.JSONException;

public interface ISpilLogik {
    String getSynligtOrd();
    String getOrdet();
    int getAntalForkerteBogstaver();
    int getAntalBrugteBogstaver();
    void opdaterSynligtOrd();
    boolean gætBogstav(String bogstav) throws JSONException;
}
