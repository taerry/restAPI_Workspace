package com.spring.json.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.json.service.JsonSchemaValidationService;

@RestController
public class JsonSchemaController {
    @Autowired
    private JsonSchemaValidationService service;

    @PostMapping("/validate")
    public String validateEvent( @RequestBody JsonNode jsonNode ){
       return service.validateJson(jsonNode);
    }
}
