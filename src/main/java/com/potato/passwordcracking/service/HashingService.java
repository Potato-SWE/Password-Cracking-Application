package com.potato.passwordcracking.service;

import com.potato.passwordcracking.constant.HashingAlgorithm;
import com.potato.passwordcracking.exception.HashingException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Hashing service class used to get the hash value of a given input and algorithm of choice.
 */

public class HashingService {

    public String getHash(String str, HashingAlgorithm algorithm) throws HashingException {

        if (str == null || algorithm == null) {
            return null;
        }

        switch (algorithm) {
            case MD5:
                return getHash(str, "MD5");
            case SHA1:
                return getHash(str, "SHA-1");
            case SHA256:
                return getHash(str, "SHA-256");
            default:
                throw new HashingException("Unsupported algorithm \"" + algorithm + "\".");
        }
    }

    private String getHash(String str, String algorithm) {

        try {

            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] bytes = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xff & bytes[i]);
                if (hex.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(hex);
            }

            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

}
