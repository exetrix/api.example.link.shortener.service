package com.cronofy.link.shortener.controller;

import com.cronofy.link.shortener.exception.RecordNotFoundException;
import com.cronofy.link.shortener.link.dao.LinkDao;
import com.cronofy.link.shortener.link.dto.Alias;
import com.cronofy.link.shortener.link.dto.AliasFactory;
import com.cronofy.link.shortener.link.dto.Target;
import com.cronofy.link.shortener.utility.RandomStringGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequestMapping("v1/api")
@RestController
public class ApiController {

    private RandomStringGenerator randomStringGenerator;
    private LinkDao linkDao;
    private AliasFactory aliasFactory;

    public ApiController(
        RandomStringGenerator randomStringGenerator,
        LinkDao linkDao,
        AliasFactory aliasFactory
    ) {
        this.randomStringGenerator = randomStringGenerator;
        this.linkDao = linkDao;
        this.aliasFactory = aliasFactory;
    }

    @PostMapping()
    public Alias add(@RequestBody Target target, HttpServletRequest request) {
        String alias = randomStringGenerator.generate();

        return aliasFactory.create(linkDao.add(alias, target.getTarget()), request);
    }

    @GetMapping("/{alias}")
    public Alias get(@PathVariable String alias, HttpServletRequest request) {
        try {
            return aliasFactory.create(linkDao.get(alias), request);
        } catch (RecordNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to locate resource with id: " + alias, e);
        }
    }

    @DeleteMapping("/{alias}")
    public Alias delete(@PathVariable String alias, HttpServletRequest request) {
        try {
            return aliasFactory.create(linkDao.delete(alias), request);
        } catch (RecordNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to locate resource with id: " + alias, e);
        }
    }
}