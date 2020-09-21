package com.example.galgeleg;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

public class Spillet_akt extends AppCompatActivity implements View.OnClickListener {
    Galgelogik logik = new Galgelogik();
    Animation animation;
    Button sendSvar;
    TextView spilInfo;
    EditText gaetteFelt;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animation = AnimationUtils.loadAnimation(this, R.anim.ryst);
        setContentView(R.layout.activity_spillet_akt);
        //Programmatisk layout

        spilInfo = findViewById(R.id.spilInfo);
        spilInfo.setText("Velkommen til et deprimerende spil, hvis du altså ikke gætter rigtigt!" +
                "\nDu skal gætte dette ord: " + logik.getSynligtOrd() +
                "\nSmid et bogstav ind i feltet nedenunder og tryk på knappen!\n");

        String velkomst = getIntent().getStringExtra("velkomst");
        if (velkomst != null) spilInfo.append(velkomst);


        gaetteFelt = findViewById(R.id.gaetteFelt);
        gaetteFelt.setHint("Det er her bogstavet skal gættes!");

        sendSvar = findViewById(R.id.sendSvar);
        sendSvar.setText("Spil");

        //sendSvar.setCompoundDrawablesRelativeWithIntrinsicBounds(android.R.drawable.ic_media_play,0,0,0);


        logik.logStatus(); //rigtige i loggen
    }

    @Override
    public void onClick(View v) {
        System.out.println("1");
        String bogstav = gaetteFelt.getText().toString();
        if(bogstav.length() != 1){
            gaetteFelt.setError("KUN ÉT BOGSTAV!");
            return;
        }
        System.out.println("2");
        logik.gætBogstav(bogstav);
        System.out.println("3");
        gaetteFelt.setText("");
        gaetteFelt.setError(null);
       /* animation.reset();
        sendSvar.clearAnimation();
        sendSvar.startAnimation(animation); */
        opdaterSkærm();
        System.out.println("4");
    }
    private void opdaterSkærm(){
        spilInfo.setText("Gæt ordet: " + logik.getSynligtOrd());
        spilInfo.append("\n\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte:" + logik.getBrugteBogstaver());

        if(logik.erSpilletVundet()){
            spilInfo.append("\nDu har vundet");
        }
        if(logik.erSpilletTabt()){
            spilInfo.append("\nDu har tabt, ordet var: " + logik.getOrdet());
        }
    }

}