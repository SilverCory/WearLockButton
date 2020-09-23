package com.coryredmond.wearlockbutton;

import android.accessibilityservice.AccessibilityService;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityKeyDetector extends AccessibilityService {

    private final String TAG = "WearLock_AccessKeyDetector";

    @Override
    public boolean onKeyEvent(KeyEvent event) {
        if (event.getAction() != KeyEvent.ACTION_UP ||
                event.getKeyCode() != KeyEvent.KEYCODE_STEM_1 ||
                event.getEventTime() - event.getDownTime() < 500) {
            return super.onKeyEvent(event);
        }

        Log.d(TAG, "LOCKING");
        ((DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE)).lockNow();
        return true;
    }


    @Override
    protected void onServiceConnected() {
        Log.i(TAG,"Service connected");

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }
}