package com.many.droidffplay;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.libsdl.app.SDLActivity;

public class Setting extends Activity{
    public final static String PREFFILE = "options";
    public final static String PARAMS = "params";
    public final static String FILEPATH = "path";
    SharedPreferences mSp;
    private EditText mEDparam;
    private EditText mEDfile;
    private Button mBTNplay;
    private Spinner mSPIurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        //getFragmentManager().beginTransaction().replace(R.id.conte, new PrefFragment()).commit();

        mSp = getSharedPreferences(PREFFILE,Context.MODE_PRIVATE);
        String pa = mSp.getString(PARAMS,"-fs -autoexit");
        String file = mSp.getString(FILEPATH,"rtmp://live.hkstv.hk.lxdns.com/live/hks");
        mEDparam = (EditText)findViewById(R.id.ed_options);
        mEDfile = (EditText)findViewById(R.id.ed_filename);
        mEDparam.setText(pa);
        mEDfile.setText(file);
        mBTNplay = (Button)findViewById(R.id.btn_play);
        mBTNplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mSp.edit().putString(PARAMS,mEDparam.getText().toString()).commit();
                mSp.edit().putString(FILEPATH,mEDfile.getText().toString()).commit();
                startActivity(new Intent().setComponent(new ComponentName(Setting.this,SDLActivity.class)));
            }
        });

        mSPIurl = (Spinner)findViewById(R.id.spi_url);
        ArrayAdapter adapter =ArrayAdapter.createFromResource(this, R.array.urls,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSPIurl.setAdapter(adapter);
        mSPIurl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String url = mSPIurl.getAdapter().getItem(position).toString();
                mEDfile.setText(url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        Log.e("many","----------fragement create------");
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("many","----------fragement create- view-----");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if ("edittext_ss".equals(preference.getKey())) {
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