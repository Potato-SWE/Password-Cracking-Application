package com.potato.passwordcracking.settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsManager {

    private class Settings {
        private String dictionaryFile = null;
    }

    private final Settings settings = new Settings();
    private final String DEFAULT_SETTINGS_PATH = System.getProperty("user.dir")+"/resources/settings.properties";
    private final String DICTIONARY_KEY = "dictionary-file";
    private boolean settingsInitialized = false;

    public SettingsManager() {

    }

    public SettingsManager(String settingsPath) {
        loadSettings(settingsPath);
        if(settingsInitialized) {
            return;
        }
        loadSettings(DEFAULT_SETTINGS_PATH);
    }

    public boolean settingsInitialized() {
        return settingsInitialized;
    }

    //TODO: save settings

    public boolean loadSettings() {
        return loadSettings(DEFAULT_SETTINGS_PATH);
    }

    public boolean loadSettings(String settingsPath) {

        if(settingsPath == null || settingsPath.length() == 0) {
            return settingsInitialized = false;
        }

        Properties props = new Properties();

        try {

            props.load(new FileInputStream(settingsPath));
            String dictionaryFile = props.getProperty(DICTIONARY_KEY);
            settings.dictionaryFile = dictionaryFile;

            return settingsInitialized = true;

        } catch(IOException e) {
            e.printStackTrace();
            return settingsInitialized = false;
        }
    }

    public String getDictionaryFile() {
        return settingsInitialized ? settings.dictionaryFile : null;
    }

}
