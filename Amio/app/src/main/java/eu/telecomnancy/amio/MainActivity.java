package eu.telecomnancy.amio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import eu.telecomnancy.amio.ui.main.SensorFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SensorFragment.newInstance())
                    .commitNow();
        }
    }
}