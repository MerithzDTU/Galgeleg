package dk.dtu.merithz.galgeleg.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import dk.dtu.merithz.galgeleg.R;
import dk.dtu.merithz.galgeleg.business.SpilLogik;
import dk.dtu.merithz.galgeleg.business.SpilHandler;
import dk.dtu.merithz.galgeleg.business.SpilLogikData;
import static dk.dtu.merithz.galgeleg.business.SpilLogikData.SpilLogikStatus;

public class SpilFragment extends Fragment implements Observer {
    private final String konsonanter = "BCDFGHJKLMNPQRSTVWXZ";
    private final String vokaler = "AEIOUYÆÅØ";
    private final int columnCount = 4;

    HashMap<String, BogstavsKnap> knapper;
    private TextView brugerNavnTekst;
    private ImageView galgemand;
    private TextView gaetteFelt;
    private GridLayout vokalerGrid;
    private GridLayout konsonanterGrid;

    private SpilHandler spilOpstarter = SpilHandler.getInstance();
    private SpilLogik spilLogik;

    String ordet;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.spillet, container, false);
        initialize(v);
        spilLogik = spilOpstarter.getSpilLogik();
        spilLogik.addObserver(this);
        brugerNavnTekst.setText(String.format("%s",spilOpstarter.getAktueltBrugerNavn()));
        gaetteFelt.setText(spilLogik.getSynligtOrd());

        return v;
    }

    private void initialize(View v){
        brugerNavnTekst = v.findViewById(R.id.brugernavnTekst);
        galgemand = v.findViewById(R.id.galgemand);
        gaetteFelt = v.findViewById(R.id.gaetteFelt);
        vokalerGrid = v.findViewById(R.id.vokaler);
        konsonanterGrid = v.findViewById(R.id.konsonanter);
        knapper = new HashMap<>();

        vokalerGrid.setColumnCount(columnCount);
        konsonanterGrid.setColumnCount(columnCount);

        initAlfabet(v,vokalerGrid,vokaler,columnCount);
        initAlfabet(v,konsonanterGrid,konsonanter,columnCount);
    }

    public void initAlfabet(View v, GridLayout gridLayout, String alfabet, int columnCount){
        for (int i = 0; i < alfabet.length(); i++) {
            String bogstav = alfabet.substring(i,i+1);
            BogstavsKnap bogstavsKnap = new BogstavsKnap(v.getContext());
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            Typeface typeface = ResourcesCompat.getFont(getContext(),R.font.knapfont);
            int size = (int) (40 * getResources().getDisplayMetrics().density);
            int margin = (int) (2 * getResources().getDisplayMetrics().density);
            params.setGravity(Gravity.CENTER);
            params.height = size;
            params.width = size;
            params.columnSpec = GridLayout.spec(i%columnCount);
            params.rowSpec = GridLayout.spec(i/columnCount);
            params.setMargins(margin,margin,margin,margin);
            bogstavsKnap.setLayoutParams(params);
            bogstavsKnap.setText(bogstav);
            bogstavsKnap.setTypeface(typeface);
            gridLayout.addView(bogstavsKnap);
            knapper.put(bogstav,bogstavsKnap);

            bogstavsKnap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmBogstav(bogstav, v);
                }
            });
        }
    }


    //PROBLEM, skal have fat i metoder fra spilogik, men alt skal håndteres gennem SpilOpstarter???
    private void confirmBogstav(String bogstav, View v){
        knapper.get(bogstav).setEnabled(false);

        if (spilLogik.gætBogstav(bogstav)){
            String tekst = "Bogstavet " + bogstav + " var korrekt!";

            Toast toast = Toast.makeText(v.getContext(), tekst, Toast.LENGTH_SHORT);
            toast.show();

        }else{
            String tekst = "Bogstavet " + bogstav + " var forkert!";

            Toast toast = Toast.makeText(v.getContext(), tekst, Toast.LENGTH_SHORT);
            toast.show();
            opdaterGalgemand();
        }
        gaetteFelt.setText(spilLogik.getSynligtOrd());
    }

    public void tabtSpil(){
        Navigation.findNavController(v).navigate(R.id.action_spilFragment_til_taberFragment);
    }

    public void vandtSpil(){
        Navigation.findNavController(v).navigate(R.id.action_spilFragment_til_vinderFragment);
    }

    public void opdaterGalgemand(){
            switch (spilLogik.getAntalForkerteBogstaver()){
                case 0:
                    galgemand.setImageResource(R.drawable.galge);
                    break;
                case 1:
                    galgemand.setImageResource(R.drawable.forkert1_sketch);
                    break;
                case 2:
                    galgemand.setImageResource(R.drawable.forkert2_sketch);
                    break;
                case 3:
                    galgemand.setImageResource(R.drawable.forkert3_sketch);
                    break;
                case 4:
                    galgemand.setImageResource(R.drawable.forkert4_sketch);
                    break;
                case 5:
                    galgemand.setImageResource(R.drawable.forkert5_sketch);
                    break;
                case 6:
                    galgemand.setImageResource(R.drawable.forkert6_sketch);
                    break;
                default:
                    galgemand.setImageResource(R.drawable.forkert6_sketch);
                    break;
            }
    }

    @Override
    public void update(Observable o, Object arg) {
        SpilLogikData data = (SpilLogikData) arg;
        System.out.println(data.toString());
        switch(data.getStatus()){
            case ord: ordet = data.getOrdet(); break;
            case vundet: vandtSpil(); break;
            case tabt: tabtSpil(); break;
        }
    }
}
