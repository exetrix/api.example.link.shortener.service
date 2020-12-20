package com.cronofy.link.shortener.integration.utility;

import com.cronofy.link.shortener.utility.RandomStringGenerator;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RandomStringGeneratorTest {
    @Test
    void generate() {
        RandomStringGenerator stringGenerator = new RandomStringGenerator(new SecureRandom(), 5);
        assertNotEquals(
            stringGenerator.generate(),
            stringGenerator.generate(),
            "Two separate calls to generate should result in different strings"
        );
    }
}