package dk.dtu.merithz.galgeleg.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

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

    public static HighscoreSaver fromActivity(Activity activity){
        return new HighscoreSaver(activity.getPreferences(Context.MODE_PRIVATE));
    }

    @Override
    public void gem(HighscoreDTO highscoreDTO) {
        editor.putInt(highscoreDTO.getBrugernavn(),highscoreDTO.getScore());
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<HighscoreDTO> getHighscores() {
        Map<String, ?> highscoreEntries = sharedPref.getAll();
        List<HighscoreDTO> highscores = new ArrayList<>();
        for (Map.Entry<String,?> entry:highscoreEntries.entrySet()) {
            highscores.add(new HighscoreDTO(entry.getKey(), Integer.parseInt(entry.getValue().toString())));
        }
        highscores.sort(new Comparator<HighscoreDTO>() {
            @Override
            public int compare(HighscoreDTO o1, HighscoreDTO o2) {
                return o1.getScore()-o2.getScore();
            }
        });
        return highscores;
    }
}
