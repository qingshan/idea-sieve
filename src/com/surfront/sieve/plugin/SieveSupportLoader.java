package com.surfront.sieve.plugin;

import com.intellij.openapi.components.ApplicationComponent;

public class SieveSupportLoader implements ApplicationComponent {
    public String getComponentName() {
        return "Sieve Support Loader";
    }

    public void initComponent() {
        registerTemplates();
    }

    public void disposeComponent() {
    }

    private void registerTemplates() {
    }
}
