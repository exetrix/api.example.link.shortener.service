package com.cronofy.link.shortener.link.dto;

import com.cronofy.link.shortener.link.dao.LinkRecord;
import com.cronofy.link.shortener.utility.LocalUrlBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class AliasFactory {
    private LocalUrlBuilder localUrlBuilder;

    public AliasFactory(LocalUrlBuilder localUrlBuilder) {
        this.localUrlBuilder = localUrlBuilder;
    }

    public Alias create(LinkRecord linkRecord, HttpServletRequest request) {
        try {
            return new Alias(
                new URL(linkRecord.getUrl()),
                localUrlBuilder.build(linkRecord.getAlias(), request)
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed url exception encountered", e);
        }
    }
}
