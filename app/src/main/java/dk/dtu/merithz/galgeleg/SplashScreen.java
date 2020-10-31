//Hjælp til splash screen er fra Aryan Ganotra's youtube kanal https://www.youtube.com/watch?v=D7c4bZzLVxs&ab_channel=AryanGanotra
package dk.dtu.merithz.galgeleg;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, MainActivity.class));
        //vigtigt at finish() huskes her, da man ellers kan gå tilbage fra hovedmenuen til splashscreen
        finish();
    }
}
