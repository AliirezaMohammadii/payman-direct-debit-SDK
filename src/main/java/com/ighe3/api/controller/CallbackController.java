package com.ighe3.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/callback")
public class CallbackController {

    @GetMapping("/create")
    public void createCallback() {
        System.out.println("it's create callback!");
    }
}
