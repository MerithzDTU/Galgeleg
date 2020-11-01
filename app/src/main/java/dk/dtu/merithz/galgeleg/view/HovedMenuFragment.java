package dk.dtu.merithz.galgeleg.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import dk.dtu.merithz.galgeleg.R;


public class HovedMenuFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rod = inflater.inflate(R.layout.hovedmenu,container,false);

        Button spilKnap = rod.findViewById(R.id.spilKnap);
        spilKnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_hovedMenuFragment_til_nytSpilFragment);
            }
        });

        return rod;
    }
}
