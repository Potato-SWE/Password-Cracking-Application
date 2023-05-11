package com.potato.passwordcracking.service;

import com.potato.passwordcracking.PasswordCrackingApplication;
import com.potato.passwordcracking.constant.HashingAlgorithm;
import com.potato.passwordcracking.exception.PasswordCrackingException;
import com.potato.passwordcracking.model.PasswordCrackingRequest;
import com.potato.passwordcracking.model.PasswordCrackingResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryCrackingServiceTest {

    private final DictionaryCrackingService dictionaryCrackingService;
    private final HashingService hashingService = new HashingService();
    private final String dictionaryFile = PasswordCrackingApplication.APPLICATION_DIRECTORY + "/resources/rockyou.txt";

    public DictionaryCrackingServiceTest() {
        this.dictionaryCrackingService = new DictionaryCrackingService(dictionaryFile);
    }

    @Test
    public void invalidRequestTest() {

        PasswordCrackingRequest request = new PasswordCrackingRequest();

        assertThrows(PasswordCrackingException.class, () -> {
            dictionaryCrackingService.crackPasswords(null);
        });

        assertThrows(PasswordCrackingException.class, () -> {
            dictionaryCrackingService.crackPasswords(request);
        });

    }

    @Test
    public void validRequestTest() {

        HashingAlgorithm algorithm = HashingAlgorithm.SHA256;

        String[] crackablePasswords = new String[]{
                "qazwsx",
                "nokia",
                "rockyou1"
        };

        String[] uncrackablePasswords = new String[]{
                "uncrackable password that is not in my dictionary file"
        };

        PasswordCrackingRequest request = new PasswordCrackingRequest();
        request.setAlgorithm(algorithm);

        for (String password : crackablePasswords) {
            String hashedPassword = hashingService.getHash(password, algorithm);
            request.addHashedPassword(hashedPassword);
        }

        for (String password : uncrackablePasswords) {
            String hashedPassword = hashingService.getHash(password, algorithm);
            request.addHashedPassword(hashedPassword);
        }

        PasswordCrackingResponse response = dictionaryCrackingService.crackPasswords(request);

        for (String password : crackablePasswords) {
            String hashedPassword = hashingService.getHash(password, algorithm);
            assertEquals(password, response.getCrackedPassword(hashedPassword));
        }

        for (String password : uncrackablePasswords) {
            String hashedPassword = hashingService.getHash(password, algorithm);
            assertNull(response.getCrackedPassword(hashedPassword));
        }
    }

}