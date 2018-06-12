package com.many.droidffplay;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;

public class Setting extends Activity{
    PreferenceFragment fr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fr = new PrefFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, fr).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

class PrefFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

    }

    //重写的以下方法请看后面讲解
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if ("edittext_ss".equals(preference.getKey())) {
            //CheckBoxPreference checkBox = (CheckBoxPreference) findPreference("select_linkage");
            //ListPreference editBox = (ListPreference) findPreference("select_city");
            //editBox.setEnabled(checkBox.isChecked());
            preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    preference.setSummary(""+newValue);
                    return true;
                }
            });
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

}