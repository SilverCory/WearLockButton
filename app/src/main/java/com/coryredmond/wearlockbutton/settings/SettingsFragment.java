package com.coryredmond.wearlockbutton.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.SeekBarPreference;

import com.coryredmond.wearlockbutton.util.KeyEventUtils;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Context context = getPreferenceManager().getContext();
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);

        DropDownPreference actionPreference = new DropDownPreference(context);
        actionPreference.setEntryValues(new CharSequence[]{
                KeyEventUtils.ACTION_DOWN,
                KeyEventUtils.ACTION_UP
        });
        actionPreference.setEntries(actionPreference.getEntryValues());
        actionPreference.setDefaultValue(KeyEventUtils.ACTION_UP);
        actionPreference.setSummaryProvider(new DropDownPreferenceSummaryProvider());
        actionPreference.setTitle("Set trigger action");
        actionPreference.setKey("triggerAction");

        DropDownPreference keyPreference = new DropDownPreference(context);
        keyPreference.setEntryValues(new CharSequence[]{
                KeyEventUtils.keyCodeToString(KeyEvent.KEYCODE_STEM_1),
                KeyEventUtils.keyCodeToString(KeyEvent.KEYCODE_STEM_2),
                KeyEventUtils.keyCodeToString(KeyEvent.KEYCODE_STEM_3),
                KeyEventUtils.keyCodeToString(KeyEvent.KEYCODE_STEM_PRIMARY),
        });
        keyPreference.setEntries(keyPreference.getEntryValues());
        actionPreference.setDefaultValue(KeyEventUtils.keyCodeToString(KeyEvent.KEYCODE_STEM_1));
        keyPreference.setSummaryProvider(new DropDownPreferenceSummaryProvider());
        keyPreference.setTitle("Set trigger key/button");
        keyPreference.setKey("triggerKey");

        SeekBarPreference minTriggerPreference = new SeekBarPreference(context);
        minTriggerPreference.setSeekBarIncrement(20);
        minTriggerPreference.setMin(0);
        minTriggerPreference.setMax(6000);
        minTriggerPreference.setDefaultValue(500);
        minTriggerPreference.setTitle("Set minimum down time (ms)");
        minTriggerPreference.setKey("triggerMin");
        minTriggerPreference.setShowSeekBarValue(true);

        SeekBarPreference maxTriggerPreference = new SeekBarPreference(context);
        maxTriggerPreference.setSeekBarIncrement(20);
        maxTriggerPreference.setMin(0);
        maxTriggerPreference.setMax(6000);
        maxTriggerPreference.setDefaultValue(1500);
        maxTriggerPreference.setTitle("Set maximum down time (ms)");
        maxTriggerPreference.setKey("triggerMax");
        maxTriggerPreference.setShowSeekBarValue(true);

        screen.addPreference(actionPreference);
        screen.addPreference(keyPreference);
        screen.addPreference(minTriggerPreference);
        screen.addPreference(maxTriggerPreference);

        setPreferenceScreen(screen);
    }

    // DropDownPreferenceSummaryProvider supplies the value as a summary.
    public static class DropDownPreferenceSummaryProvider implements Preference.SummaryProvider<DropDownPreference> {
        @Override
        public CharSequence provideSummary(DropDownPreference preference) {
            return preference.getValue();
        }
    }

}
