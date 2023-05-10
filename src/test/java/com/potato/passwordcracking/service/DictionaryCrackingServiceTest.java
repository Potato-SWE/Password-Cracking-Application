package com.potato.passwordcracking.service;

import com.potato.passwordcracking.constant.HashingAlgorithm;
import com.potato.passwordcracking.exception.PasswordCrackingException;
import com.potato.passwordcracking.model.PasswordCrackingRequest;
import com.potato.passwordcracking.model.PasswordCrackingResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryCrackingServiceTest {

    private final DictionaryCrackingService dictionaryCrackingService;
    private final HashingService hashingService = new HashingService();
    private final String dictionaryFile = System.getProperty("user.dir") + "/resources/rockyou.txt";

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
        HashingAlgorithm sha256 = HashingAlgorithm.SHA256;
        String[] crackablePasswords = new String[]{
                "qazwsx",
                "nokia",
                "rockyou1"
        };

        String[] uncrackablePasswords = new String[]{
                "uncrackable password that is not in my dictionary file"
        };

        PasswordCrackingRequest request = new PasswordCrackingRequest();
        request.setAlgorithm(sha256);

        for (String password : crackablePasswords) {
            String hashedPassword = hashingService.getHash(password, sha256);
            request.addHashedPassword(hashedPassword);
        }

        for (String password : uncrackablePasswords) {
            String hashedPassword = hashingService.getHash(password, sha256);
            request.addHashedPassword(hashedPassword);
        }

        PasswordCrackingResponse response = dictionaryCrackingService.crackPasswords(request);

        for (String password : crackablePasswords) {
            String hashedPassword = hashingService.getHash(password, sha256);
            assertEquals(password, response.getCrackedPassword(hashedPassword));
        }

        for (String password : uncrackablePasswords) {
            String hashedPassword = hashingService.getHash(password, sha256);
            assertNull(response.getCrackedPassword(hashedPassword));
        }
    }

}