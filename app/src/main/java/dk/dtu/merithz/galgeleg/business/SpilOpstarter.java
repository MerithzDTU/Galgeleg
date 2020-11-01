package dk.dtu.merithz.galgeleg.business;

import dk.dtu.merithz.galgeleg.data.HentOrd;

public class SpilOpstarter implements ISpilOpstarter {
    private HentOrd hentOrd;
    public SpilOpstarter(HentOrd hentOrd) {
        this.hentOrd = hentOrd;
    }

    @Override
    public void startSpil() {

    }

    @Override
    public void genstartSpil() {

    }

}
