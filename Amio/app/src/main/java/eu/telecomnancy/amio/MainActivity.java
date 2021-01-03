package eu.telecomnancy.amio;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.polling.Constants;
import eu.telecomnancy.amio.polling.PollingService;
import eu.telecomnancy.amio.ui.main.IMoteUpdateWatcher;
import eu.telecomnancy.amio.ui.main.MainViewModel;
import eu.telecomnancy.amio.ui.main.MoteUpdateBroadcastReceiver;
import eu.telecomnancy.amio.ui.main.mote.MoteListFragment;

/**
 * Default activity started at launch
 */
public class MainActivity extends AppCompatActivity implements IMoteUpdateWatcher {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = MainActivity.class.getName();
    private Intent mPollingServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        // Replace the container layout with the Recycler view fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, MoteListFragment.newInstance())
                .commitNow();

        // Set the mote update broadcast receiver
        registerReceiver(
                new MoteUpdateBroadcastReceiver(this),
                new IntentFilter(Constants.Broadcast.UPDATED_DATA)
        );

        mPollingServiceIntent = new Intent(this, PollingService.class);
        startService(mPollingServiceIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.main_app_bar_menu, menu);
        return true;
    }

    @Override
    public void onMotesUpdate(List<Mote> motes) {
        Log.d(TAG, "Broadcast received");

        MainViewModel model = new ViewModelProvider(this)
                .get(MainViewModel.class);

        model.setMotes(motes);
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
    protected void onDestroy() {
        stopService(mPollingServiceIntent);
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
