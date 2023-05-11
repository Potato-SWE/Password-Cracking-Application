package com.potato.passwordcracking.model;

import java.util.HashMap;

public class PasswordCrackingResponse {

    private final HashMap<String, String> crackedPasswordMap = new HashMap<>();
    private String message = null;

    public PasswordCrackingResponse() {

    }

    public PasswordCrackingResponse(String message) {
        this.message = message;
    }

    public void add(String hashedPassword, String crackedPassword) {
        if (hashedPassword == null || crackedPassword == null || hashedPassword.length() == 0) {
            return;
        }
        crackedPasswordMap.put(hashedPassword, crackedPassword);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public HashMap<String, String> getCrackedPasswordMap() {
        return crackedPasswordMap;
    }

    public String getCrackedPassword(String hashedPassword) {
        return crackedPasswordMap.get(hashedPassword);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------- RESPONSE ----------\nMessage: \"" + message + "\"\n----- CRACKED PASSWORDS -----\n");
        stringBuilder.append(crackedPasswordMap);
        return stringBuilder.toString();
    }
}
