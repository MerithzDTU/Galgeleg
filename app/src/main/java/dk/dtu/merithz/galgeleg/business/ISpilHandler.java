package dk.dtu.merithz.galgeleg.business;

import android.app.Activity;

public interface ISpilHandler {

    void initSpil(String sværhedsgrad, Activity activity);
    void startSpil();
}
