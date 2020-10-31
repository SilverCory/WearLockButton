package com.coryredmond.wearlockbutton.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import com.coryredmond.wearlockbutton.util.KeyEventUtils;

public class AccessibilityKeyDetector extends AccessibilityService implements SharedPreferences.OnSharedPreferenceChangeListener {

    private DevicePolicyManager devicePolicyManager;

    private int triggerAction;
    private int triggerKeyCode;
    private long triggerDelayMin;
    private long triggerDelayMax;

    @Override
    public boolean onKeyEvent(KeyEvent event) {
        if (event.getAction() != triggerAction || event.getKeyCode() != triggerKeyCode) {
            return super.onKeyEvent(event);
        }

        long triggerTime = event.getEventTime() - event.getDownTime();
        if (triggerTime < triggerDelayMin || triggerTime > triggerDelayMax){
            return super.onKeyEvent(event);
        }

        devicePolicyManager.lockNow();
        return true;
    }


    @Override
    protected void onServiceConnected() {
        devicePolicyManager = ((DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE));
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        loadSettings();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        loadSettings(); // Reload because preferences changed.
    }

    private void loadSettings() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        triggerAction = KeyEventUtils.actionFromString(preferences.getString("triggerAction", KeyEventUtils.ACTION_UP));
        triggerKeyCode = KeyEventUtils.keyCodeFromString(preferences.getString("triggerKey", KeyEventUtils.keyCodeToString(KeyEvent.KEYCODE_STEM_1)));
        triggerDelayMin = preferences.getInt("triggerMin", 500);
        triggerDelayMax = preferences.getInt("triggerMax", 1500);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Nothing to do here...
    }

    @Override
    public void onInterrupt() {
        // Nothing to do here...
    }
}