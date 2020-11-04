package eu.telecomnancy.amio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import eu.telecomnancy.amio.polling.PollingService;

public class SettingsActivity extends AppCompatActivity {

    // TODO: move to main activity once created
    private Intent pollingServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // TODO: move to main activity once created
        pollingServiceIntent = new Intent(this, PollingService.class);
        startService(pollingServiceIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // TODO: move to main activity once created
        stopService(pollingServiceIntent);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
