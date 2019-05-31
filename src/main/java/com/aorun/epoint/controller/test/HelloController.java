package com.aorun.epoint.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping(value = "/test")
    public String query() {
        return "hello";
    }
}