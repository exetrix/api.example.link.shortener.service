package com.cronofy.link.shortener.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class LocalUrlBuilderTest {

    private static final String ALIAS = "SomeAlias";
    private static final String SERVER_NAME = "localhost";
    private static final String SCHEME = "http";


    private LocalUrlBuilder builder;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        builder = new LocalUrlBuilder();

        request = Mockito.mock(HttpServletRequest.class);
    }

    @Test
    void buildHttps() throws MalformedURLException {
        Mockito.when(request.getScheme()).thenReturn(SCHEME);
        Mockito.when(request.getServerName()).thenReturn(SERVER_NAME);
        Mockito.when(request.getServerPort()).thenReturn(443);

        assertEquals(SCHEME + "://" + SERVER_NAME + "/" + ALIAS, builder.build(ALIAS, request).toString());
    }

    @Test
    void buildHttp() throws MalformedURLException {
        Mockito.when(request.getScheme()).thenReturn(SCHEME);
        Mockito.when(request.getServerName()).thenReturn(SERVER_NAME);
        Mockito.when(request.getServerPort()).thenReturn(80);

        assertEquals(SCHEME + "://" + SERVER_NAME + "/" + ALIAS, builder.build(ALIAS, request).toString());
    }

    @Test
    void buildWithCustomPort() throws MalformedURLException {
        int port = 1234;

        Mockito.when(request.getScheme()).thenReturn(SCHEME);
        Mockito.when(request.getServerName()).thenReturn(SERVER_NAME);
        Mockito.when(request.getServerPort()).thenReturn(port);

        assertEquals(SCHEME + "://" + SERVER_NAME + ":" + port + "/" + ALIAS, builder.build(ALIAS, request).toString());
    }
}