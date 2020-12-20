package com.cronofy.link.shortener.configuration;

import com.cronofy.link.shortener.utility.RandomStringGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class UtilityConfiguration {
    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }

    @Bean
    public RandomStringGenerator randomStringGenerator(SecureRandom random) {
        return new RandomStringGenerator(random, 5);
    }
}
