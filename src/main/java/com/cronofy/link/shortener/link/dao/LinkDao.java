package com.cronofy.link.shortener.link.dao;

import com.cronofy.link.shortener.dynamodb.DynamoDbHelper;
import com.cronofy.link.shortener.dynamodb.FieldName;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class LinkDao {
    private DynamoDbClient dynamoDbClient;
    private DynamoDbHelper dynamoDbHelper;
    private LinkRecordFactory linkRecordFactory;

    public LinkDao(DynamoDbClient dynamoDbClient, DynamoDbHelper dynamoDbHelper, LinkRecordFactory linkRecordFactory) {
        this.dynamoDbClient = dynamoDbClient;
        this.dynamoDbHelper = dynamoDbHelper;
        this.linkRecordFactory = linkRecordFactory;
    }

    public LinkRecord add(String alias, URL target) {
        String targetString = target.toString();
        HashMap<String, AttributeValue> item = new HashMap<>();

        item.put(FieldName.KEY, dynamoDbHelper.createStringAttribute(alias));
        item.put(FieldName.target, dynamoDbHelper.createStringAttribute(targetString));

        dynamoDbHelper.createPutItemRequest(item);

        return linkRecordFactory.create(alias, targetString);
    }

    public LinkRecord delete(String alias) {
        LinkRecord record = get(alias);
        dynamoDbClient.deleteItem(dynamoDbHelper.createDeleteItemRequest(alias));
        return record;
    }

    public LinkRecord get(String alias) {
        GetItemResponse response = dynamoDbClient.getItem(dynamoDbHelper.createGetItemRequest(alias));
        Map<String, AttributeValue> item = response.item();

        return linkRecordFactory.create(
            dynamoDbHelper.getStringFromItem(item, FieldName.KEY),
            dynamoDbHelper.getStringFromItem(item, FieldName.target)
        );
    }
}
