package com.cronofy.link.shortener.utility;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class LocalUrlBuilder {
    public URL build(String alias, HttpServletRequest request) throws MalformedURLException {
        String urlString;
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            urlString = String.format("%s://%s:%d/%s", request.getScheme(), request.getServerName(), request.getServerPort(), alias);
        } else {
            urlString = String.format("%s://%s/%s", request.getScheme(), request.getServerName(), alias);
        }

        return new URL(urlString);
    }
}
