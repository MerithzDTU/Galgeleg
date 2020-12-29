package dk.dtu.merithz.galgeleg.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;

import dk.dtu.merithz.galgeleg.R;

public class BogstavsKnap extends androidx.appcompat.widget.AppCompatButton {

    @Override
    public void setEnabled(boolean enabled) {
        setAlpha(enabled?1:0.5f);
        super.setEnabled(enabled);
    }

    public BogstavsKnap(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
        setBackgroundColor(getResources().getColor(R.color.ColorRed));
        setTextColor(getResources().getColor(R.color.colorWhite));
        setTextSize(32);
        setPadding(2,2,2,2);
        setTypeface(getTypeface(),Typeface.BOLD);

    }



}
