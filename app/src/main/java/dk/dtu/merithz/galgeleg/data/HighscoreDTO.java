package dk.dtu.merithz.galgeleg.data;

public class HighscoreDTO {
    private String brugernavn;
    private int score;

    public HighscoreDTO(String brugernavn, int score){
        this.brugernavn = brugernavn;
        this.score = score;
    }


    public String getBrugernavn(){
        return brugernavn;
    }

    public int getScore(){
        return score;
    }


    @Override
    public String toString() {
        return "Highscore{" +
                "brugernavn='" + brugernavn + '\'' +
                ", score=" + score +
                '}';
    }
}
