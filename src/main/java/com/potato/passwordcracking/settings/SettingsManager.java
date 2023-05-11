package com.potato.passwordcracking.settings;

import com.potato.passwordcracking.PasswordCrackingApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsManager {

    private class Settings {
        private String dictionaryFile = null;
    }

    private final Settings settings = new Settings();
    private final String DICTIONARY_KEY = "dictionary-file";
    private boolean settingsInitialized = false;

    public SettingsManager() {

    }

    public SettingsManager(String settingsPath) {
        loadSettings(settingsPath);
    }

    public boolean settingsInitialized() {
        return settingsInitialized;
    }

    //TODO: save settings

    public void loadSettings(String settingsPath) {

        if(settingsPath == null || settingsPath.length() == 0) {
            return;
        }

        Properties props = new Properties();

        try {

            props.load(new FileInputStream(settingsPath));
            String dictionaryFile = props.getProperty(DICTIONARY_KEY);
            settings.dictionaryFile = dictionaryFile;

            settingsInitialized = true;
            return;
        } catch(IOException e) {
            e.printStackTrace();
            settings.dictionaryFile = null;
            settingsInitialized = false;
            return;
        }
    }

    public String getDictionaryFile() {
        return settingsInitialized ? settings.dictionaryFile : null;
    }

}
