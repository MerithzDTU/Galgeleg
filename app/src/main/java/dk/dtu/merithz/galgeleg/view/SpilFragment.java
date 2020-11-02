package dk.dtu.merithz.galgeleg.view;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import dk.dtu.merithz.galgeleg.R;
import dk.dtu.merithz.galgeleg.business.SpilLogik;
import dk.dtu.merithz.galgeleg.business.SpilOpstarter;

public class SpilFragment extends Fragment {
    private final String konsonanter = "BCDFGHJKLMNPQRSTVWXZ";
    private final String vokaler = "AEIOUYÆÅØ";
    private final int columnCount = 4;

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
        spilLogik.gætBogstav(bogstav);
        if(spilLogik.erSpilletTabt()){
            Navigation.findNavController(v).navigate(R.id.action_spilFragment_til_taberFragment);
        }
        if (spilLogik.erSpilletVundet()){
            Navigation.findNavController(v).navigate(R.id.action_spilFragment_til_vinderFragment);
        }
        gaetteFelt.setText(spilLogik.getSynligtOrd());
    }
}
