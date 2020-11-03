package dk.dtu.merithz.galgeleg.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import dk.dtu.merithz.galgeleg.R;
import dk.dtu.merithz.galgeleg.business.SpilLogik;
import dk.dtu.merithz.galgeleg.business.SpilOpstarter;

public class VinderFragment extends Fragment {

    private TextView brugerNavnVinder;
    private ImageView galgemand;
    private TextView vinderTekst;
    private TextView vinderInfo;
    private Button nytspilKnap;

    private SpilOpstarter spilOpstarter = SpilOpstarter.getInstance();
    private SpilLogik spilLogik;

    private void initialize(View v){
        brugerNavnVinder = v.findViewById(R.id.brugernavnVinder_TV);
        galgemand = v.findViewById(R.id.galgelegVinder);
        vinderTekst = v.findViewById(R.id.vinder_TV);
        vinderInfo = v.findViewById(R.id.vinderInfo_TV);
        nytspilKnap = v.findViewById(R.id.vinderNytSpilKnap);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.vinder,container, false);
        initialize(v);
        brugerNavnVinder.setText(String.format("%s",spilOpstarter.getAktueltBrugerNavn()));
        spilLogik = spilOpstarter.getSpilLogik();
        vinderInfo.setText("Du gættede " + spilLogik.getOrdet() + " på " + spilLogik.getBrugteBogstaver().size() + " forsøg.");

        nytspilKnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_vinderFragment_til_nytSpilFragment);
            }
        });

        OnBackPressedCallback tilbagekald = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(v).navigate(R.id.action_vinderFragment_til_hovedMenuFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),tilbagekald);

        return v;
    }
}
