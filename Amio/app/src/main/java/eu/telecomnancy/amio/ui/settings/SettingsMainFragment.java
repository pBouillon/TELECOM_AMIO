package eu.telecomnancy.amio.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.ui.settings.components.timepicker.TimePickerPreference;
import eu.telecomnancy.amio.ui.settings.components.timepicker.TimePickerPreferenceDialog;

/**
 * A fragment representing a field to be changed in the preferences
 */
public class SettingsMainFragment extends PreferenceFragmentCompat {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.title_activity_settings));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        if (preference instanceof TimePickerPreference) {
            TimePickerPreferenceDialog dialog = TimePickerPreferenceDialog.newInstance(preference.getKey());
            dialog.setTargetFragment(this, 0);
            dialog.show(getParentFragmentManager(), "TimePickerDialog");
        } else {
            super.onDisplayPreferenceDialog(preference);
        }

    }
}
