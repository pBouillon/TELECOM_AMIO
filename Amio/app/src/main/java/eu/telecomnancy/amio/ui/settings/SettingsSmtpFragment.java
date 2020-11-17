package eu.telecomnancy.amio.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.concurrent.Executors;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.notification.contexts.EventContext;
import eu.telecomnancy.amio.notification.contexts.NotificationContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.notification.notifiers.INotifier;
import eu.telecomnancy.amio.notification.notifiers.email.SmtpNotifier;
import eu.telecomnancy.amio.polling.contexts.PollingContext;

public class SettingsSmtpFragment extends PreferenceFragmentCompat {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.title_activity_smtp));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Preference button = findPreference(getString(R.string.smtp_try_it_key));
        if (button != null) {
            button.setOnPreferenceClickListener(preference -> {
                // Async task will be deprecated in API 30
                // Networking is not allowed in main thread
                Executors.newSingleThreadExecutor().execute(() -> {
                    boolean sended = true;
                    NotificationContext notificationContext = new NotificationContext(NotificationType.EMAIL, new EventContext(new PollingContext(getContext())));
                    INotifier mailer = new SmtpNotifier(notificationContext);
                    try {
                        mailer.sendNotification();
                    } catch (Throwable e) {
                        sended = false;
                    }
                    String toastText;
                    if (sended) {
                        toastText = getString(R.string.toast_test_mail_success);
                    } else {
                        toastText = getString(R.string.toast_test_mail_fail);
                    }
                    // The toast must be throw in the UI thread
                    getActivity().runOnUiThread(() -> Toast.makeText(getContext(), toastText, Toast.LENGTH_LONG).show());
                });
                return false;
            });
        }
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.smtp_preferences, rootKey);
    }
}