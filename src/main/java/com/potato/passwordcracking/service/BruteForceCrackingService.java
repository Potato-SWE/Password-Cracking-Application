package com.potato.passwordcracking.service;

import com.potato.passwordcracking.constant.HashingAlgorithm;
import com.potato.passwordcracking.exception.PasswordCrackingException;
import com.potato.passwordcracking.model.PasswordCrackingRequest;
import com.potato.passwordcracking.model.PasswordCrackingResponse;

import java.util.HashSet;

public class BruteForceCrackingService extends PasswordCrackingService {

    private final char min = '!';
    private final char max = '~';
    private boolean cancelAttack = false;

    private final int maxChars;

    public BruteForceCrackingService() {
        this.maxChars = 8;
    }

    public BruteForceCrackingService(int maxChars) {
        this.maxChars = maxChars;
    }

    @Override
    public PasswordCrackingResponse crackPasswords(PasswordCrackingRequest request) throws PasswordCrackingException {

        if (request == null || request.getAlgorithm() == null || request.getHashedPasswords() == null) {
            throw new PasswordCrackingException("Unable to crack passwords with invalid request.");
        }

        HashSet<String> hashedPasswords = new HashSet<>(request.getHashedPasswords());
        HashingAlgorithm algorithm = request.getAlgorithm();

        return crackPasswords(algorithm, hashedPasswords);
    }

    private PasswordCrackingResponse crackPasswords(HashingAlgorithm algorithm, HashSet<String> hashedPasswords) throws PasswordCrackingException {

        PasswordCrackingResponse response = new PasswordCrackingResponse();

        int numberOfChars = 2;
        while (numberOfChars <= maxChars && !cancelAttack) {
            System.out.println("brute force: " + numberOfChars);
            bruteForceWithGivenChars(algorithm, hashedPasswords, numberOfChars++, response);
        }

        return response;
    }

    private void bruteForceWithGivenChars(HashingAlgorithm algorithm, HashSet<String> hashedPasswords, int numberOfCharacters, PasswordCrackingResponse response) {
        if (numberOfCharacters < 1) {
            return;
        }

        char[] charArray = new char[numberOfCharacters];
        int index = 0;

        while (index < charArray.length && !cancelAttack) {

            String permutation = new String(charArray);
            String hashedPermutation = getHash(permutation, algorithm);

            if (hashedPasswords.contains(hashedPermutation)) {
                response.add(hashedPermutation, permutation);
            }

            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] < max) {
                    charArray[i]++;
                    break;
                }

                charArray[i] = min;

                if (i == index) {
                    index++;
                }
            }
        }
    }
}
