package com.potato.passwordcracking.service;

import com.potato.passwordcracking.constant.HashingAlgorithm;
import com.potato.passwordcracking.model.PasswordCrackingRequest;
import com.potato.passwordcracking.model.PasswordCrackingResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BruteForceCrackingServiceTest {

    private final BruteForceCrackingService bruteForceCrackingService = new BruteForceCrackingService(3);
    private final HashingService hashingService = new HashingService();

    @Test
    public void crackPasswordsTest() {

        HashingAlgorithm algorithm = HashingAlgorithm.SHA256;

        String[] crackablePasswords = new String[] {
                "var",
                "arm",
                "hop"
        };

        String[] uncrackablePasswords = new String[] {
                "too many characters to be crackable."
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

        PasswordCrackingResponse response = bruteForceCrackingService.crackPasswords(request);

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