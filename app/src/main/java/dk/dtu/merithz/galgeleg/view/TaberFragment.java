package dk.dtu.merithz.galgeleg.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import dk.dtu.merithz.galgeleg.R;
import dk.dtu.merithz.galgeleg.business.SpilLogik;
import dk.dtu.merithz.galgeleg.business.SpilOpstarter;

public class TaberFragment extends Fragment {

    private TextView brugerNavnTaber;
    private ImageView galgemand;
    private TextView taberTekst;
    private TextView rigtigeord;
    private Button nytspilKnap;

    private SpilOpstarter spilOpstarter = SpilOpstarter.getInstance();
    private SpilLogik spilLogik;

    private void initialize(View v){
        brugerNavnTaber = v.findViewById(R.id.brugernavnTaber_TV);
        galgemand = v.findViewById(R.id.galgelegTaber);
        taberTekst = v.findViewById(R.id.taber_TV);
        rigtigeord = v.findViewById(R.id.rigtigeord_TV);
        nytspilKnap = v.findViewById(R.id.taberNytSpilKnap);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.taber,container,false);
        initialize(v);
        brugerNavnTaber.setText(String.format("%s",spilOpstarter.getAktueltBrugerNavn()));
        spilLogik = spilOpstarter.getSpilLogik();
        rigtigeord.setText("Ordet var: " + spilLogik.getOrdet());

        nytspilKnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_taberFragment_til_nytSpilFragment);
            }
        });

        return v;
    }
}
