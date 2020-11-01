package dk.dtu.merithz.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;


import dk.dtu.merithz.galgeleg.business.SpilOpstarter;


public class MainActivity extends AppCompatActivity {
    SpilOpstarter spilOpstarter = SpilOpstarter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}