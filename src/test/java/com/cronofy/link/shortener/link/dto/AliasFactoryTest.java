package com.cronofy.link.shortener.link.dto;

import com.cronofy.link.shortener.link.dao.LinkRecord;
import com.cronofy.link.shortener.utility.LocalUrlBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class AliasFactoryTest {

    private LocalUrlBuilder urlBuilder;
    private AliasFactory factory;
    private LinkRecord record;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        urlBuilder = Mockito.mock(LocalUrlBuilder.class);

        factory = new AliasFactory(urlBuilder);

        record = Mockito.mock(LinkRecord.class);
        request = Mockito.mock(HttpServletRequest.class);
    }

    @Test
    void create() throws MalformedURLException {
        String alias = "SomeAlias";
        String target = "https://google.co.uk";
        URL expectedUrl = new URL("http://localhost");

        Mockito.when(record.getAlias()).thenReturn(alias);
        Mockito.when(record.getUrl()).thenReturn(target);

        Mockito.when(urlBuilder.build(Mockito.anyString(), Mockito.any())).thenReturn(expectedUrl);

        Alias aliasDto = factory.create(record, request);

        Mockito.verify(urlBuilder).build(alias, request);

        assertEquals(expectedUrl, aliasDto.getAlias());
        assertEquals(target, aliasDto.getTarget().toString());
    }
}