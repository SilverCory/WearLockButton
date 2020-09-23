package com.coryredmond.wearlockbutton;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.input.WearableButtons;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private DevicePolicyManager deviceManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        deviceManger = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAccessibilityPermission();
    }

    public boolean checkAccessibilityPermission() {
        int accessEnabled=0;
        try {
            accessEnabled = Settings.Secure.getInt(this.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if (accessEnabled==0) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            /** request permission via start activity for result */
            startActivity(intent);
            return false;
        } else {
            return true;
        }
    }

}