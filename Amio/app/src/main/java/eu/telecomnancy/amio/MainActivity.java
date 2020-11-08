package eu.telecomnancy.amio;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.polling.Constants;
import eu.telecomnancy.amio.polling.PollingService;
import eu.telecomnancy.amio.ui.main.MainViewModel;
import eu.telecomnancy.amio.ui.main.MoteUpdateAware;
import eu.telecomnancy.amio.ui.main.MoteUpdateBroadcastReceiver;
import eu.telecomnancy.amio.ui.main.SensorFragment;

/**
 * TODO doc
 */
public class MainActivity extends AppCompatActivity implements MoteUpdateAware {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = MainActivity.class.getName();

    /**
     * TODO doc
     */
    private Intent pollingServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        Toolbar myToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SensorFragment.newInstance())
                    .commitNow();
        }

        registerReceiver(
                new MoteUpdateBroadcastReceiver(this),
                new IntentFilter(Constants.Broadcast.UPDATED_DATA)
        );

        pollingServiceIntent = new Intent(this, PollingService.class);
        startService(pollingServiceIntent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_button) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.main_app_bar_menu, menu);

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(pollingServiceIntent);
    }

    @Override
    public void onMotesUpdate(List<Mote> motes) {
        Log.d(TAG, "Broadcast received");

        MainViewModel model = new ViewModelProvider(this)
                .get(MainViewModel.class);

        model.setMotes(motes);
    }

}
