package com.potato.passwordcracking.model;

import java.util.HashMap;

public class PasswordCrackingResponse {

    private final HashMap<String, String> crackedPasswordMap = new HashMap<>();

    public void add(String hashedPassword, String crackedPassword) {
        if (hashedPassword == null || crackedPassword == null || hashedPassword.length() == 0) {
            return;
        }
        crackedPasswordMap.put(hashedPassword, crackedPassword);
    }

    public HashMap<String, String> getCrackedPasswordMap() {
        return crackedPasswordMap;
    }

    public String getCrackedPassword(String hashedPassword) {
        return crackedPasswordMap.get(hashedPassword);
    }
}
