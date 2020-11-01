package dk.dtu.merithz.galgeleg.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import dk.dtu.merithz.galgeleg.business.GalgeLogik;
import dk.dtu.merithz.galgeleg.R;

public class Spillet_akt extends AppCompatActivity {
    GalgeLogik logik = new GalgeLogik();
    Animation animation;
    Button sendSvar;
    TextView spilInfo;
    TextView ordet;
    EditText gaetteFelt;
    ImageView imageview;
    Button genstart;
    int maxBogstav = 1;


    private void initialize() {
        animation = AnimationUtils.loadAnimation(this, R.anim.ryst);
        spilInfo = findViewById(R.id.spilInfo);
        imageview = findViewById(R.id.imageView);
        gaetteFelt = findViewById(R.id.gaetteFelt);
        sendSvar = findViewById(R.id.sendSvar);
        ordet = findViewById(R.id.ordet);
        genstart = findViewById(R.id.genstart);

        genstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genstart();
            }
        });

        sendSvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gætBogstav();
            }
        });

        gaetteFelt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    gætBogstav();
                    return true;
                }
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spillet_akt);
        initialize();
        genstart();
    }

    public void genstart(){
        logik.nulstil();
        spilInfo.setText("Gæt ordet gemt med stjerner!" + "\nIndtast et bogstav i feltet og prøv lykken!\n");
        ordet.setText(logik.getSynligtOrd());
        gaetteFelt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxBogstav)});
        logik.logStatus(); //rigtige i loggen

        genstart.setVisibility(View.INVISIBLE);
        sendSvar.setVisibility(View.VISIBLE);
        gaetteFelt.setVisibility(View.VISIBLE);
        imageview.setImageResource(R.drawable.galge);
    }

    public void gætBogstav(){
        final String bogstav = gaetteFelt.getText().toString();
        logik.gætBogstav(bogstav);
        gaetteFelt.setText("");
        gaetteFelt.setError(null);
        animation.reset();

        opdaterSkærm(bogstav);
    }

//    private void skjulTastatur(){
//        if(gaetteFelt != null){
//            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(gaetteFelt.getWindowToken(),0);
//        }
//    }

    private void opdaterSkærm(String bogstav) {
        ordet.setText(logik.getSynligtOrd());
        spilInfo.setText("\n\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte: " + logik.getForkerteBogstaver());

        if (logik.erSpilletVundet()) {
            spilInfo.clearComposingText();
            spilInfo.append("\nDu har vundet med " + logik.getBrugteBogstaver().size() + " forsøg!");
            gaetteFelt.setVisibility(View.INVISIBLE);
            skjulTastatur();
            sendSvar.setVisibility(View.INVISIBLE);
            genstart.setVisibility(View.VISIBLE);

        }
        if(logik.erSpilletTabt()) {
            spilInfo.append("\nDu har tabt! Ordet var: " + logik.getOrdet());
            gaetteFelt.setVisibility(View.INVISIBLE);
            gaetteFelt.clearFocus();
            skjulTastatur();
            sendSvar.setVisibility(View.INVISIBLE);
            genstart.setVisibility(View.VISIBLE);
        }

        if (!logik.getOrdet().contains(bogstav)) {
            sendSvar.clearAnimation();
            sendSvar.startAnimation(animation);
            switch (logik.getAntalForkerteBogstaver()){
                case 0:
                    imageview.setImageResource(R.drawable.galge);
                    break;
                case 1:
                    imageview.setImageResource(R.drawable.forkert1);
                    break;
                case 2:
                    imageview.setImageResource(R.drawable.forkert2);
                    break;
                case 3:
                    imageview.setImageResource(R.drawable.forkert3);
                    break;
                case 4:
                    imageview.setImageResource(R.drawable.forkert4);
                    break;
                case 5:
                    imageview.setImageResource(R.drawable.forkert5);
                    break;
                case 6:
                    imageview.setImageResource(R.drawable.forkert6);
                    break;
                default:
                    imageview.setImageResource(R.drawable.forkert6);
                    break;
            }
        }
    }
}