package dk.dtu.merithz.galgeleg.view;

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
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.HashMap;

import dk.dtu.merithz.galgeleg.R;
import dk.dtu.merithz.galgeleg.business.SpilLogik;
import dk.dtu.merithz.galgeleg.business.SpilOpstarter;

public class SpilFragment extends Fragment {
    private final String konsonanter = "BCDFGHJKLMNPQRSTVWXZ";
    private final String vokaler = "AEIOUYÆÅØ";
    private final int columnCount = 4;

    HashMap<String, BogstavsKnap> knapper;
    private TextView brugerNavnTekst;
    private ImageView galgemand;
    private TextView gaetteFelt;
    private GridLayout vokalerGrid;
    private GridLayout konsonanterGrid;


    private SpilOpstarter spilOpstarter = SpilOpstarter.getInstance();
    private SpilLogik spilLogik;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.spillet, container, false);
        initialize(v);
        brugerNavnTekst.setText(String.format("%s",spilOpstarter.getAktueltBrugerNavn()));
        spilLogik = spilOpstarter.getSpilLogik();
        gaetteFelt.setText(spilLogik.getSynligtOrd());


        return v;
    }


    //PROBLEM, skal have fat i metoder fra spilogik, men alt skal håndteres gennem SpilOpstarter???
    private void confirmBogstav(String bogstav, View v){
        knapper.get(bogstav).setEnabled(false);

        if (spilLogik.gætBogstav(bogstav)){
            String tekst = "Bogstavet " + bogstav + " var korrekt!";
            int tid = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(v.getContext(), tekst, tid);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

        }else{
            String tekst = "Bogstavet " + bogstav + " var forkert!";
            int tid = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(v.getContext(), tekst, tid);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            opdaterGalgemand();
        }

        if(spilLogik.erSpilletTabt()){
            Navigation.findNavController(v).navigate(R.id.action_spilFragment_til_taberFragment);
        }
        if (spilLogik.erSpilletVundet()){
            Navigation.findNavController(v).navigate(R.id.action_spilFragment_til_vinderFragment);
        }
        gaetteFelt.setText(spilLogik.getSynligtOrd());
    }

    public void opdaterGalgemand(){

            switch (spilLogik.getAntalForkerteBogstaver()){
                case 0:
                    galgemand.setImageResource(R.drawable.galge);
                    break;
                case 1:
                    galgemand.setImageResource(R.drawable.forkert1);
                    break;
                case 2:
                    galgemand.setImageResource(R.drawable.forkert2);
                    break;
                case 3:
                    galgemand.setImageResource(R.drawable.forkert3);
                    break;
                case 4:
                    galgemand.setImageResource(R.drawable.forkert4);
                    break;
                case 5:
                    galgemand.setImageResource(R.drawable.forkert5);
                    break;
                case 6:
                    galgemand.setImageResource(R.drawable.forkert6);
                    break;
                default:
                    galgemand.setImageResource(R.drawable.forkert6);
                    break;
            }
    }
}
