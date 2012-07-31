package com.surfront.sieve.plugin;

import com.intellij.CommonBundle;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ResourceBundle;

public class SieveBundle {
    private static Reference<ResourceBundle> bundle;

    private SieveBundle() {
    }

    public static String message(String key, Object... params) {
        return CommonBundle.message(getBundle(), key, params);
    }

    private static ResourceBundle getBundle() {
        ResourceBundle bundle = null;
        if (SieveBundle.bundle != null) bundle = SieveBundle.bundle.get();
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("com.surfront.sieve.plugin.SieveBundle");
            SieveBundle.bundle = new SoftReference<ResourceBundle>(bundle);
        }
        return bundle;
    }
}

