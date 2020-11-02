package dk.dtu.merithz.galgeleg.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import dk.dtu.merithz.galgeleg.R;
import dk.dtu.merithz.galgeleg.business.SpilOpstarter;

public class NytSpilFragment extends Fragment {
    SpilOpstarter spilOpstarter = SpilOpstarter.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.nyt_spil,container,false);

        TextView indtastBrugerNavn = v.findViewById(R.id.indtastBrugerNavn_TV);

        Button startSpil = v.findViewById(R.id.startSpil);
        startSpil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spilOpstarter.initSpil("123");
                spilOpstarter.startSpil();
                Navigation.findNavController(v).navigate(R.id.action_nytSpilFragment_til_spilFragment);
            }
        });

        return v;
    }
}
