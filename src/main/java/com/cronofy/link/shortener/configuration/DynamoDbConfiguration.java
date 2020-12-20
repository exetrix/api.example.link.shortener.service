package com.cronofy.link.shortener.configuration;

import com.cronofy.link.shortener.configuration.properties.DynamoDbProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DynamoDbConfiguration {
    @Bean
    public DynamoDbClient dynamoDbClient(DynamoDbProperties dynamoDbProperties) throws URISyntaxException {
        DynamoDbClientBuilder builder = DynamoDbClient
            .builder()
            .credentialsProvider(
                StaticCredentialsProvider.create(AwsBasicCredentials.create("ExampleAccessKey", "ExampleSecretKey"))
            ).region(dynamoDbProperties.getRegion());

        if (dynamoDbProperties.isEndpointOverride()) {
            //noinspection ConstantConditions
            builder.endpointOverride(new URI(dynamoDbProperties.getEndpoint()));
        }

        return builder.build();
    }
}
