package com.hochschule.digimarkt.utility;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptUtility {
    public static String encodePassword(String password) {
        // Encode password using Base64
        byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }
}
