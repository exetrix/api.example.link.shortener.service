package com.cronofy.link.shortener.configuration;

import com.cronofy.link.shortener.configuration.properties.DynamoDbProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

@Configuration
public class DynamoDbConfiguration {
    @Bean
    public DynamoDbClient dynamoDbClient(DynamoDbProperties dynamoDbProperties) {
        DynamoDbClientBuilder builder = DynamoDbClient.builder().region(dynamoDbProperties.getRegion());

        if (dynamoDbProperties.isEndpointOverride()) {
            //builder.endpointOverride(dynamoDbProperties.getEndpoint());
        }

        return builder.build();
    }
}
