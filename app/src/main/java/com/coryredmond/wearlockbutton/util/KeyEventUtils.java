package com.coryredmond.wearlockbutton.util;

import android.view.KeyEvent;

public class KeyEventUtils {

    public static String ACTION_UP = "ACTION_UP";
    public static String ACTION_DOWN = "ACTION_DOWN";

    public static int actionFromString(String actionStr) {
        if (actionStr == null) {
            return -1;
        }

        actionStr = actionStr.trim();
        if (ACTION_UP.equalsIgnoreCase(actionStr)) {
            return KeyEvent.ACTION_UP;
        } else if (ACTION_DOWN.equalsIgnoreCase(actionStr)) {
            return KeyEvent.ACTION_DOWN;
        }

        return -1;
    }

    public static String actionToString(int action) {
        switch (action) {
            case KeyEvent.ACTION_UP: return ACTION_UP;
            case KeyEvent.ACTION_DOWN: return ACTION_DOWN;
            default: return null;
        }
    }

    public static int keyCodeFromString(String keyCodeStr) {
        return KeyEvent.keyCodeFromString(keyCodeStr);
    }

    public static String keyCodeToString(int keyCode) {
        return KeyEvent.keyCodeToString(keyCode);
    }
}
