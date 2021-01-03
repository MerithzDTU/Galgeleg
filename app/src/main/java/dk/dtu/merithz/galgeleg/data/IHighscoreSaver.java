package dk.dtu.merithz.galgeleg.data;

import org.json.JSONException;

import java.util.List;

public interface IHighscoreSaver {
    void gem(HighscoreDTO highscoreDTO) throws JSONException;
    List<HighscoreDTO> getHighscores() throws JSONException;
}
