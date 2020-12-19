package com.cronofy.link.shortener.link.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@NoArgsConstructor
public class Alias extends Target {
    private URL alias;

    public Alias(URL target, URL alias) {
        super(target);
        this.alias = alias;
    }
}
