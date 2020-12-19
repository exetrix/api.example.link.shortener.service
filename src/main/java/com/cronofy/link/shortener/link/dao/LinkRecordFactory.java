package com.cronofy.link.shortener.link.dao;

import org.springframework.stereotype.Component;

@Component
public class LinkRecordFactory {
    public LinkRecord create(String alias) {
        return new LinkRecord(alias);
    }

    public LinkRecord create(String alias, String target) {
        return new LinkRecord(alias, target);
    }
}
