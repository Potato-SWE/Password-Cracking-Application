package com.potato.passwordcracking.model;

import com.potato.passwordcracking.constant.CrackingStrategy;
import com.potato.passwordcracking.constant.HashingAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class PasswordCrackingRequest {

    private final List<String> hashedPasswords = new ArrayList<>();
    private HashingAlgorithm algorithm = null;
    private CrackingStrategy strategy = null;

    public List<String> getHashedPasswords() {
        return hashedPasswords;
    }

    public HashingAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void addHashedPassword(String hashedPassword) {
        if (hashedPassword == null || hashedPassword.length() == 0) {
            return;
        }
        hashedPasswords.add(hashedPassword);
    }

    public void setHashedPasswords(List<String> hashedPasswords) {
        if (hashedPasswords == null) {
            return;
        }

        for (int i = 0; i < hashedPasswords.size(); i++) {
            addHashedPassword(hashedPasswords.get(i));
        }

    }

    public void setAlgorithm(HashingAlgorithm algorithm) {
        if (algorithm == null) {
            return;
        }
        this.algorithm = algorithm;
    }

    public CrackingStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(CrackingStrategy strategy) {
        this.strategy = strategy;
    }
}
