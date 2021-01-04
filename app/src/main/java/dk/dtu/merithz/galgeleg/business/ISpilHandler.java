package dk.dtu.merithz.galgeleg.business;

import android.app.Activity;

import dk.dtu.merithz.galgeleg.data.Sværhedsgrad;

public interface ISpilHandler {
    void initSpil(Sværhedsgrad sværhedsgrad, Activity activity);
    void startSpil();
    void slutSpil();
}
