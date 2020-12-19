package com.cronofy.link.shortener.link.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkRecord {
    private String alias;
    private String url;

    public LinkRecord(String alias) {
        this.alias = alias;
    }
}
