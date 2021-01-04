package dk.dtu.merithz.galgeleg.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class HighscoreSaver implements IHighscoreSaver{
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private static HighscoreSaver instance = null;


    public HighscoreSaver(SharedPreferences sharedPref){
        this.sharedPref = sharedPref;
        editor = sharedPref.edit();
    }

    //Simplest form of factory, even though it generates the one and only kind of highscore
    public static HighscoreSaver fromActivity(Activity activity){
        return new HighscoreSaver(activity.getPreferences(Context.MODE_PRIVATE));
    }

    @Override
    public void gem(HighscoreDTO highscoreDTO) throws JSONException {
        editor.putString(highscoreDTO.getBrugernavn(),highscoreDTO.toJSON());
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<HighscoreDTO> getHighscores() throws JSONException {
        Map<String, ?> highscoreEntries = sharedPref.getAll();
        List<HighscoreDTO> highscores = new ArrayList<>();
        for (Map.Entry<String,?> entry:highscoreEntries.entrySet()) {
            highscores.add(HighscoreDTO.fromJSON(entry.getValue().toString()));
        }
        highscores.sort(new Comparator<HighscoreDTO>() {
            @Override
            public int compare(HighscoreDTO o1, HighscoreDTO o2) {
                return o2.getScore()-o1.getScore();
            }
        });
        return highscores;
    }
}
