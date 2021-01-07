package dk.dtu.merithz.galgeleg.data;

//ENUMs til sværhedsgraderne
public enum Sværhedsgrad {
    LET ("Let",1),
    MIDDEL("Middel",2),
    SVÆR("Svær",3);

    private final String navn;
    private final int value;

    Sværhedsgrad(String navn, int value) {
        this.navn = navn;
        this.value = value;
    }

    public String getNavn() {
        return navn;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return navn;
    }
}
