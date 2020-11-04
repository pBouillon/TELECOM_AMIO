package eu.telecomnancy.amio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.polling.PollingService;
import eu.telecomnancy.amio.ui.main.MainViewModel;
import eu.telecomnancy.amio.ui.main.MoteUpdateAware;
import eu.telecomnancy.amio.ui.main.MoteUpdateBroadcastReciever;
import eu.telecomnancy.amio.ui.main.SensorFragment;

public class MainActivity extends AppCompatActivity implements MoteUpdateAware {

    private Intent pollingServiceIntent;

    private static final String TAG = MainActivity.class.getSimpleName();

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

        registerReceiver(
                new MoteUpdateBroadcastReciever(this),
                new IntentFilter(PollingService.MOTE_DATA_UPDATE_ACTION)
        );

        pollingServiceIntent = new Intent(this, PollingService.class);
        startService(pollingServiceIntent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings_button:
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

    @Override
    public void onMotesUpdate(List<Mote> motes) {
        Log.d(TAG,"Broadcast recieved");
        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);
        model.setMotes(motes);
    }
}