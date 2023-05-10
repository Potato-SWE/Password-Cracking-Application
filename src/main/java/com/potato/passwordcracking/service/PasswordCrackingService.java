package com.potato.passwordcracking.service;

import com.potato.passwordcracking.constant.HashingAlgorithm;
import com.potato.passwordcracking.exception.HashingException;
import com.potato.passwordcracking.model.PasswordCrackingRequest;
import com.potato.passwordcracking.model.PasswordCrackingResponse;

public abstract class PasswordCrackingService {

    private final HashingService hashingService = new HashingService();

    public abstract PasswordCrackingResponse crackPasswords(PasswordCrackingRequest passwordCrackingRequest);

    protected String getHash(String str, HashingAlgorithm algorithm) {
        try {
            return hashingService.getHash(str, algorithm);
        } catch(HashingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
