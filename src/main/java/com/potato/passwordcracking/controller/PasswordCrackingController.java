package com.potato.passwordcracking.controller;

import com.potato.passwordcracking.exception.PasswordCrackingException;
import com.potato.passwordcracking.model.PasswordCrackingRequest;
import com.potato.passwordcracking.model.PasswordCrackingResponse;
import com.potato.passwordcracking.service.BruteForceCrackingService;
import com.potato.passwordcracking.service.DictionaryCrackingService;

public class PasswordCrackingController {

    private final DictionaryCrackingService dictionaryCrackingService;
    private final BruteForceCrackingService bruteForceCrackingService;

    public PasswordCrackingController() {
        this.dictionaryCrackingService = new DictionaryCrackingService();
        this.bruteForceCrackingService = new BruteForceCrackingService(3);
    }

    public PasswordCrackingResponse crackPasswords(PasswordCrackingRequest request) {
        if (request == null) {
            return new PasswordCrackingResponse("Unable to crack passwords with null request.");
        }

        if (request.getStrategy() == null) {
            return new PasswordCrackingResponse("Unable to crack passwords with null strategy.");
        }

        try {

            switch (request.getStrategy()) {
                case BRUTE_FORCE:
                    return bruteForceCrackingService.crackPasswords(request);
                case DICTIIONARY:
                    return dictionaryCrackingService.crackPasswords(request);
                default:
                    return new PasswordCrackingResponse("Unsupported password cracking service.");
            }

        } catch (PasswordCrackingException e) {
            return new PasswordCrackingResponse(e.getMessage());
        }
    }

}
