package eu.telecomnancy.amio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import eu.telecomnancy.amio.polling.PollingService;
import eu.telecomnancy.amio.ui.main.SensorFragment;

public class MainActivity extends AppCompatActivity {

    private Intent pollingServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SensorFragment.newInstance())
                    .commitNow();
        }


        pollingServiceIntent = new Intent(this, PollingService.class);
        startService(pollingServiceIntent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_app_bar_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(pollingServiceIntent);
    }
}