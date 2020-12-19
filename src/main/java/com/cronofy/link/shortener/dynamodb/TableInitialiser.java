package com.cronofy.link.shortener.dynamodb;

import com.cronofy.link.shortener.configuration.properties.DynamoDbProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@Profile("!test")
@Component
public class TableInitialiser implements CommandLineRunner {

    private DynamoDbClient dynamoDbClient;
    private DynamoDbProperties dynamoDbProperties;

    public TableInitialiser(DynamoDbClient dynamoDbClient, DynamoDbProperties dynamoDbProperties) {
        this.dynamoDbClient = dynamoDbClient;
        this.dynamoDbProperties = dynamoDbProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        CreateTableRequest request = CreateTableRequest.builder()
            .attributeDefinitions(AttributeDefinition.builder()
                .attributeName(FieldName.KEY)
                .attributeType(ScalarAttributeType.S)
                .build())
            .keySchema(KeySchemaElement.builder()
                .attributeName(FieldName.KEY)
                .keyType(KeyType.HASH)
                .build())
            .provisionedThroughput(ProvisionedThroughput.builder().build())
            .tableName(dynamoDbProperties.getTableName())
            .build();

       dynamoDbClient.createTable(request);
    }
}
