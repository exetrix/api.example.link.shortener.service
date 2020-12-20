package com.cronofy.link.shortener.link.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@NoArgsConstructor
public class Target {
    private URL target;

    public Target(URL target) {
        this.target = target;
    }
}
