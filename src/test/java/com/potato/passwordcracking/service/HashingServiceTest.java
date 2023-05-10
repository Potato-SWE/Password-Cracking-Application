package com.potato.passwordcracking.service;

import com.potato.passwordcracking.constant.HashingAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashingServiceTest {

    private final HashingService hashingService = new HashingService();

    @Test
    public void hashNullStringTest() {
        assertNull(hashingService.getHash(null, HashingAlgorithm.MD5));
    }

    @Test
    public void hashWithNullAlgorithmTest() {
        assertNull(hashingService.getHash("test", null));
    }

    @Test
    public void validateMD5Test() {
        String expected = "098f6bcd4621d373cade4e832627b4f6";
        String input = "test";

        assertEquals(expected, hashingService.getHash(input, HashingAlgorithm.MD5));
    }

    @Test
    public void validateSHA1Test() {
        String expected = "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3";
        String input = "test";

        assertEquals(expected, hashingService.getHash(input, HashingAlgorithm.SHA1));
    }

    @Test
    public void validateSHA256Test() {
        String expected = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";
        String input = "test";

        assertEquals(expected, hashingService.getHash(input, HashingAlgorithm.SHA256));
    }
}