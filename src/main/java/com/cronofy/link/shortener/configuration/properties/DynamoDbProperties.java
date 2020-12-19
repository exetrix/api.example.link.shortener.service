package com.cronofy.link.shortener.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;
import software.amazon.awssdk.regions.Region;

import java.net.URI;

@Data
@ConfigurationProperties(prefix = "dynamodb")
public class DynamoDbProperties {

    private String region;
    @Nullable
    private String endpoint;
    private String tableName;

    public Region getRegion() {
        return Region.of(region);
    }

    public boolean isEndpointOverride() {
        return endpoint != null;
    }
}
