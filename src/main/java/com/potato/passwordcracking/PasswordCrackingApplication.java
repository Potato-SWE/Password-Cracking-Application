package com.potato.passwordcracking;

import com.potato.passwordcracking.settings.SettingsManager;
import com.potato.passwordcracking.view.PasswordCrackingView;

public class PasswordCrackingApplication {

    public static final SettingsManager settingsManager = new SettingsManager();
    public static final String APPLICATION_DIRECTORY = System.getProperty("user.dir").replace('\\', '/');

    public static void main(String[] args) {
        if (args.length > 0) {
            settingsManager.loadSettings(args[0]);
        }

        if (!settingsManager.settingsInitialized()) {
            settingsManager.loadSettings(APPLICATION_DIRECTORY + "/resources/settings.properties");
        }
        if (!settingsManager.settingsInitialized()) {
            System.out.println("Error loading settings.");
            return;
        } else {
            System.out.println("settings loaded: " + settingsManager.getDictionaryFile() + " -> launching application.");
            launchApplication();
        }
    }

    private static void launchApplication() {
        PasswordCrackingView passwordCrackingView = new PasswordCrackingView(settingsManager);
        passwordCrackingView.run();
    }
}
