package com.cronofy.link.shortener.unit.utility;

import com.cronofy.link.shortener.utility.RandomStringGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class RandomStringGeneratorTest {
    private SecureRandom random;

    @BeforeEach
    void setUp() {
        random = Mockito.mock(SecureRandom.class);
    }

    @Test
    void generate() {
        int stringLength = 5;
        RandomStringGenerator stringGenerator = new RandomStringGenerator(random, stringLength);

        Mockito.when(random.nextInt()).thenReturn(10);

        String result = stringGenerator.generate();

        assertEquals(stringLength, result.length());
    }
}