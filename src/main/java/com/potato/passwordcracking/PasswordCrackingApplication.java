package com.potato.passwordcracking;

import com.potato.passwordcracking.settings.SettingsManager;
import com.potato.passwordcracking.view.PasswordCrackingView;

public class PasswordCrackingApplication {

    public final static SettingsManager settingsManager = new SettingsManager();

    public static void main(String[] args) {
        if (args.length > 0) {
            settingsManager.loadSettings(args[0]);
        }

        if (!settingsManager.settingsInitialized()) {
            settingsManager.loadSettings();
        }
        if (!settingsManager.settingsInitialized()) {
            System.out.println("Error loading settings.");
            return;
        } else {
            System.out.println("settings loaded: " + settingsManager.getDictionaryFile()+" -> launching application.");
            launchApplication();
        }
    }

    private static void launchApplication() {
        PasswordCrackingView passwordCrackingView = new PasswordCrackingView();
        passwordCrackingView.run();
    }
}
