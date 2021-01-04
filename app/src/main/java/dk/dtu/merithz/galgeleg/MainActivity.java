package dk.dtu.merithz.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import org.json.JSONException;

import dk.dtu.merithz.galgeleg.business.SpilHandler;
import dk.dtu.merithz.galgeleg.data.HighscoreDTO;
import dk.dtu.merithz.galgeleg.data.HighscoreSaver;


public class MainActivity extends AppCompatActivity {
    SpilHandler spilOpstarter = SpilHandler.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            for (HighscoreDTO highscore : HighscoreSaver.fromActivity(this).getHighscores()) {
                System.out.println(highscore);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}