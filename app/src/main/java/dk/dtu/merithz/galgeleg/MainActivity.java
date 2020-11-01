package dk.dtu.merithz.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dk.dtu.merithz.galgeleg.R;
import dk.dtu.merithz.galgeleg.view.Highscore_akt;
import dk.dtu.merithz.galgeleg.view.Hjaelp_akt;
import dk.dtu.merithz.galgeleg.view.Spillet_akt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button spilKnap, hjaelpKnap, highscoreKnap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hjaelpKnap = findViewById(R.id.hjaelpKnap);
        hjaelpKnap.setText("Hjælp");

        spilKnap = findViewById(R.id.spilKnap);
        spilKnap.setText("Spil");

        highscoreKnap = findViewById(R.id.highscoreKnap);
        highscoreKnap.setText("Highscores");

        hjaelpKnap.setOnClickListener(this);
        spilKnap.setOnClickListener(this);
        highscoreKnap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println("Der er sgu trykket");

        if (v == hjaelpKnap){
            Intent i = new Intent(this, Hjaelp_akt.class);
            startActivity(i);
        } else if( v == spilKnap){
            Intent i = new Intent(this, Spillet_akt.class);
            i.putExtra("velkomst","\n\n Hejsa fra Hovedmenu(MainActivity.class)\n");
            startActivity(i);
        }else if ( v == highscoreKnap){
            Intent i = new Intent(this, Highscore_akt.class);
            startActivity(i);
        }
    }
}