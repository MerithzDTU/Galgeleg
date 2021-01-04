package dk.dtu.merithz.galgeleg.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dk.dtu.merithz.galgeleg.R;
import dk.dtu.merithz.galgeleg.business.SpilHandler;
import dk.dtu.merithz.galgeleg.data.Sværhedsgrad;

public class NytSpilFragment extends Fragment {
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());  // håndtag til forgrundstråden
    SpilHandler spilOpstarter = SpilHandler.getInstance();

    TextView indtastBrugerNavnTekst;
    EditText indtastBrugerNavnFelt;
    Spinner svaerhedsgradValg;
    ProgressBar progressBar;
    Button startSpil;

    //Counter til progressbaren
    int counter = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.nyt_spil,container,false);

        indtastBrugerNavnTekst = v.findViewById(R.id.indtastBrugerNavn_TV);

        indtastBrugerNavnFelt = v.findViewById(R.id.brugernavn_ET);

        svaerhedsgradValg = v.findViewById(R.id.svaerhedsgradSpinner);

        progressBar = v.findViewById(R.id.indlaesningCirkel);

        startSpil = v.findViewById(R.id.startSpil);

        indtastBrugerNavnFelt.setText(spilOpstarter.getAktueltBrugerNavn());

        svaerhedsgradValg.setAdapter(new ArrayAdapter<>(v.getContext(), android.R.layout.simple_spinner_item, Sværhedsgrad.values()));

        startSpil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brugernavn = indtastBrugerNavnFelt.getText().toString();
                Sværhedsgrad svaerhedsgrad = (Sværhedsgrad) svaerhedsgradValg.getSelectedItem();
                System.out.println(svaerhedsgrad);
                if(brugernavn.length() < 1){
                    String tekst = "Indtast venligst et brugernavn";
                    int tid = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(v.getContext(), tekst, tid);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else{
                    startSpil.setEnabled(false);
                    progressBar();
                    bgThread.execute(()->{
                        spilOpstarter.setAktueltBrugerNavn(brugernavn);
                        spilOpstarter.initSpil(svaerhedsgrad, getActivity());
                        uiThread.post(()->{
                            spilOpstarter.startSpil();
                            Navigation.findNavController(v).navigate(R.id.action_nytSpilFragment_til_spilFragment);
                        });
                    });
                }
            }
        });

        OnBackPressedCallback tilbagekald = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(v).navigate(R.id.action_nytSpilFragment_til_hovedmenuFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),tilbagekald);

        return v;
    }

    public void progressBar(){
        //Gør progressbaren synlig igen
        progressBar.setVisibility(View.VISIBLE);

        //Timer til progressbaren
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
            counter++;
            progressBar.setProgress(counter);

            if (counter == 100){
                t.cancel();
            }
            }
        };
        t.schedule(tt,0,100);
    }
}
