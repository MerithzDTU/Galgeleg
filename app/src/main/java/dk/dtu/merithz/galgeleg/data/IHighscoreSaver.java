package dk.dtu.merithz.galgeleg.data;

import java.util.List;

public interface IHighscoreSaver {
    void gem(HighscoreDTO highscoreDTO);
    List<HighscoreDTO> getHighscores();
}
