package com.cronofy.link.shortener.utility;

import java.security.SecureRandom;

public class RandomStringGenerator {
    private final static String CHARACTERS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
    private final static int CHARACTER_COUNT = CHARACTERS.length();

    private SecureRandom random;
    private int stringLength;

    public RandomStringGenerator(SecureRandom random, int stringLength) {
        this.random = random;
        this.stringLength = stringLength;
    }

    public String generate() {
        StringBuilder stringBuilder = new StringBuilder(stringLength);

        for(int i = 0; i < stringLength; i++) {
            stringBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTER_COUNT)));
        }

        return stringBuilder.toString();
    }
}
