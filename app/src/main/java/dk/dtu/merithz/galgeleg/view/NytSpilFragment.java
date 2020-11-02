package dk.dtu.merithz.galgeleg.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dk.dtu.merithz.galgeleg.R;
import dk.dtu.merithz.galgeleg.business.SpilOpstarter;

public class NytSpilFragment extends Fragment {
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());  // håndtag til forgrundstråden
    SpilOpstarter spilOpstarter = SpilOpstarter.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.nyt_spil,container,false);

        TextView indtastBrugerNavnTekst = v.findViewById(R.id.indtastBrugerNavn_TV);

        EditText indtastBrugerNavnFelt = v.findViewById(R.id.brugernavn_ET);

        Spinner svaerhedsgradValg = v.findViewById(R.id.svaerhedsgradSpinner);

        ProgressBar progressBar = v.findViewById(R.id.indlaesningCirkel);

        indtastBrugerNavnFelt.setText(spilOpstarter.getAktueltBrugerNavn());

        Button startSpil = v.findViewById(R.id.startSpil);
        startSpil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brugernavn = indtastBrugerNavnFelt.getText().toString();
                String svaerhedsgrad = getResources().getStringArray(R.array.svaerhedsgraderVærdier)[svaerhedsgradValg.getSelectedItemPosition()];
                System.out.println(svaerhedsgrad);
                if(brugernavn.length() < 1){
                    String tekst = "Indtast venligst et brugernavn";
                    int tid = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(v.getContext(), tekst, tid);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else{
                    bgThread.execute(()->{
                        spilOpstarter.setAktueltBrugerNavn(brugernavn);
                        spilOpstarter.initSpil(svaerhedsgrad);
                        uiThread.post(()->{
                            spilOpstarter.startSpil();
                            Navigation.findNavController(v).navigate(R.id.action_nytSpilFragment_til_spilFragment);
                        });
                    });

                }

            }
        });

        return v;
    }
}
