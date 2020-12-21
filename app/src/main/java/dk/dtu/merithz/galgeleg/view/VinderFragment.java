package dk.dtu.merithz.galgeleg.view;

import android.content.res.Resources;
import android.graphics.Color;
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
import dk.dtu.merithz.galgeleg.business.SpilHandler;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class VinderFragment extends Fragment {

    private TextView brugerNavnVinder;
    private ImageView galgemand;
    private TextView vinderTekst;
    private TextView vinderInfo;
    private Button nytspilKnap;

    private SpilHandler spilOpstarter = SpilHandler.getInstance();
    private SpilLogik spilLogik;

    private KonfettiView konfettiView;

    private void initialize(View v){
        brugerNavnVinder = v.findViewById(R.id.brugernavnVinder_TV);
        galgemand = v.findViewById(R.id.galgelegVinder);
        vinderTekst = v.findViewById(R.id.vinder_TV);
        vinderInfo = v.findViewById(R.id.vinderInfo_TV);
        nytspilKnap = v.findViewById(R.id.vinderNytSpilKnap);
        konfettiView = v.findViewById(R.id.viewKonfetti);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.vinder, container, false);
        initialize(v);
        spilLogik = spilOpstarter.getSpilLogik();
        brugerNavnVinder.setText(String.format("%s",spilOpstarter.getAktueltBrugerNavn()));
        vinderInfo.setText("Du gættede " + spilLogik.getOrdet() + " på " + spilLogik.getAntalBrugteBogstaver() + " forsøg.");
        startKonfetti();
        System.out.println(Resources.getSystem().getDisplayMetrics().widthPixels);
        nytspilKnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_vinderFragment_til_nytSpilFragment);
                konfettiView.reset();
            }
        });

        OnBackPressedCallback tilbagekald = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(v).navigate(R.id.action_vinderFragment_til_hovedMenuFragment);
                konfettiView.reset();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),tilbagekald);

        return v;
    }

    public void startKonfetti(){
        float width = Resources.getSystem().getDisplayMetrics().widthPixels;
        konfettiView.build()
                .addColors(Color.BLUE, Color.WHITE, Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 10f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(0f,  width, 0f, 0f)
                .streamFor(300, 5000L);
    }
}
