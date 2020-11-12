package eu.telecomnancy.amio.ui.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import eu.telecomnancy.amio.R;

/**
 * A fragment representing a field to be changed in the preferences
 */
public class SettingsMainFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

}
