package dk.dtu.merithz.galgeleg.view;

import android.os.Bundle;
import android.view.KeyEvent;
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

        Button hjaelpKnap = rod.findViewById(R.id.hjaelpKnap);
        hjaelpKnap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_hovedMenuFragment_til_hjaelpFragment);
            }
        });

        Button highscoreKnap = rod.findViewById(R.id.highscoreKnap);
        highscoreKnap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_hovedMenuFragment_til_highscoreFragment);
            }
        });

        /*
        Kode lånt fra https://stackoverflow.com/a/36128834
        Gør det muligt ikke at gå tilbage i stacken når man trykker tilbage
        E.g. Spilleren er gået igennem nytspil->spil->vundet/tabt spil->Hovedmenuen, hvis dette ikke er lavet kan man gå tilbage fra hovedmenuen->tabt/vundet spil osv.
         */
        rod.setFocusableInTouchMode(true);
        rod.requestFocus();
        rod.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    //Hvis KEYCODE_BACK returnerer true, så vil det ikke være muligt at trykke på tilbage knappen fra fragmentet.
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                }
                return false;
            }
        });

        return rod;
    }

}
