package dk.dtu.merithz.galgeleg.view;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

import dk.dtu.merithz.galgeleg.R;
import dk.dtu.merithz.galgeleg.data.HighscoreDTO;
import dk.dtu.merithz.galgeleg.data.HighscoreSaver;
import dk.dtu.merithz.galgeleg.data.Sværhedsgrad;


public class HighScoreFragment extends Fragment {
    private RecyclerView highscoresRV;
    private TextView highscoresTitle;
    private TextView highscoreBruger;
    private TextView highscoreScore;
    private HighscoreSaver highscoreSaver;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.highscore,container, false);
        highscoresRV = v.findViewById(R.id.highscores_RV);
        highscoresTitle = v.findViewById(R.id.highScoresTitle_TV);
        highscoreBruger = v.findViewById(R.id.highscoreBruger_TV);
        highscoreScore = v.findViewById(R.id.highscoreScore_TV);

        highscoreSaver = HighscoreSaver.fromActivity(requireActivity());

        recyclerView = (RecyclerView) v.findViewById(R.id.highscores_RV);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        try {
            mAdapter = new MyAdapter(highscoreSaver.getHighscores(), getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(mAdapter);

        return v;
    }
}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final Context context;
    private List<HighscoreDTO> mDataset;

        public static class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView brugerTextView;
            public TextView scoreTextView;

            public MyViewHolder(View v){
                super(v);
                brugerTextView = v.findViewById(R.id.highscoreBruger_TV);
                scoreTextView = v.findViewById(R.id.highscoreScore_TV);
            }
        }

        public MyAdapter(List<HighscoreDTO> myDataset, Context context){
            this.context = context;
            this.mDataset = myDataset;
        }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.highscore_view, parent ,false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position ) {
            HighscoreDTO highscoreDTO = mDataset.get(position);
            Typeface typeface = ResourcesCompat.getFont(context, R.font.knapfont);
            holder.brugerTextView.setTypeface(typeface);
            holder.scoreTextView.setTypeface(typeface);
            holder.brugerTextView.setText(highscoreDTO.getBrugernavn());
            holder.scoreTextView.setText(String.valueOf(highscoreDTO.getScore()));

            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    final HighScoreDialog highscoreDialog = new HighScoreDialog(context,highscoreDTO);
                    highscoreDialog.show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

class HighScoreDialog extends Dialog {
    TextView highscoreTid;
    TextView highscoreBrugernavn;
    TextView highscoreScore;
    TextView highscoreOrd;
    TextView highscoreAntalForsøg;
    TextView highscoreAntalForkert;
    TextView highscoreSværhedsgrad;

    HighscoreDTO highscoreDTO;
    public HighScoreDialog(@NonNull Context context, HighscoreDTO highscoreDTO) {
        super(context);
        this.highscoreDTO = highscoreDTO;
        setContentView(R.layout.highscore_dialog);

        highscoreBrugernavn = findViewById(R.id.dialog_highscoreBrugernavn);
        highscoreScore = findViewById(R.id.dialog_highscoreScore);
        highscoreOrd = findViewById(R.id.dialog_highscoreOrd);
        highscoreTid = findViewById(R.id.dialog_highscoreTid);
        highscoreAntalForsøg = findViewById(R.id.dialog_highscoreForsøg);
        highscoreAntalForkert = findViewById(R.id.dialog_highscoreForkert);
        highscoreSværhedsgrad = findViewById(R.id.dialog_highscoreSværhedsgrad);

        String tid = (String) android.text.format.DateFormat.format("dd-MM-yyyy", highscoreDTO.getTimestamp());

        highscoreBrugernavn.setText(highscoreDTO.getBrugernavn());
        highscoreScore.setText(String.valueOf(highscoreDTO.getScore()));
        highscoreAntalForsøg.setText(String.valueOf(highscoreDTO.getAntalForsøg()));
        highscoreAntalForkert.setText(String.valueOf(highscoreDTO.getAntalForkert()));
        highscoreOrd.setText('\"' + highscoreDTO.getOrdet() +'\"');
        highscoreSværhedsgrad.setText(highscoreDTO.getSværhedsgrad().getNavn());
        highscoreTid.setText(tid);
    }
}
