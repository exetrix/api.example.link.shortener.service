package com.cronofy.link.shortener.dynamodb;

import com.cronofy.link.shortener.configuration.properties.DynamoDbProperties;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Collections;
import java.util.Map;

@Component
public class DynamoDbHelper {
    private DynamoDbProperties dynamoDbProperties;

    public DynamoDbHelper(DynamoDbProperties dynamoDbProperties) {
        this.dynamoDbProperties = dynamoDbProperties;
    }

    public PutItemRequest createPutItemRequest(Map<String, AttributeValue> record) {
        return PutItemRequest.builder().tableName(dynamoDbProperties.getTableName()).item(record).build();
    }

    public DeleteItemRequest createDeleteItemRequest(String key) {
        return DeleteItemRequest
            .builder()
            .tableName(dynamoDbProperties.getTableName())
            .key(Collections.singletonMap(FieldName.KEY, createStringAttribute(key)))
            .build();
    }

    public GetItemRequest createGetItemRequest(String key) {
        return GetItemRequest
            .builder()
            .tableName(dynamoDbProperties.getTableName())
            .key(Collections.singletonMap(FieldName.KEY, createStringAttribute(key)))
            .build();
    }

    public String getStringFromItem(Map<String, AttributeValue> record, String key) {
        return record.get(key).s();
    }

    public AttributeValue createStringAttribute(String value) {
        return AttributeValue.builder().s(value).build();
    }
}
