package com.potato.passwordcracking.service;

import com.potato.passwordcracking.PasswordCrackingApplication;
import com.potato.passwordcracking.constant.HashingAlgorithm;
import com.potato.passwordcracking.exception.HashingException;
import com.potato.passwordcracking.model.PasswordCrackingRequest;
import com.potato.passwordcracking.model.PasswordCrackingResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class DictionaryCrackingService extends PasswordCrackingService {

    private final String dictionaryFile;

    public DictionaryCrackingService() {
        this.dictionaryFile = PasswordCrackingApplication.settingsManager.getDictionaryFile();
    }

    public DictionaryCrackingService(String dictionaryFile) {
        this.dictionaryFile = dictionaryFile;
    }

    @Override
    public PasswordCrackingResponse crackPasswords(PasswordCrackingRequest request) {

        if (request == null || request.getAlgorithm() == null || request.getHashedPasswords() == null) {
            return new PasswordCrackingResponse();
        }

        HashSet<String> hashedPasswords = new HashSet<>(request.getHashedPasswords());
        HashingAlgorithm algorithm = request.getAlgorithm();

        return crackPasswords(algorithm, hashedPasswords);
    }

    private PasswordCrackingResponse crackPasswords(HashingAlgorithm algorithm, HashSet<String> hashedPasswords) {

        PasswordCrackingResponse response = new PasswordCrackingResponse();

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(dictionaryFile));

            while(bufferedReader.ready()) {

                String line = bufferedReader.readLine();
                String hashedLine = getHash(line, algorithm);

                if(hashedLine != null && hashedPasswords.contains(hashedLine)) {
                    response.add(hashedLine, line);
                }
            }

            bufferedReader.close();
            return response;

        } catch (IOException | HashingException e) {
            e.printStackTrace();
            return response;
        }
    }
}
