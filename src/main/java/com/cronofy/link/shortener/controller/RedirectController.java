package com.cronofy.link.shortener.controller;

import com.cronofy.link.shortener.link.dto.Alias;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectController {

    private ApiController apiController;

    public RedirectController(ApiController apiController) {
        this.apiController = apiController;
    }

    @GetMapping("/{alias}")
    public void redirect(@PathVariable String alias, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        Alias aliasDto = apiController.get(alias, request);
        httpServletResponse.setHeader("Location", aliasDto.getTarget().toString());
        httpServletResponse.setStatus(302);
    }
}
