package dk.dtu.merithz.galgeleg.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;

import dk.dtu.merithz.galgeleg.R;

public class BogstavsKnap extends androidx.appcompat.widget.AppCompatButton {



    public BogstavsKnap(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
        setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        setTextColor(getResources().getColor(R.color.colorWhite));
        setTextSize(14);
        setPadding(2,2,2,2);
        setTypeface(getTypeface(),Typeface.BOLD);

    }



}
