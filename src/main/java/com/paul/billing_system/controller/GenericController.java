package com.paul.billing_system.controller;

import com.paul.billing_system.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generics")
public class GenericController {
    private final GenericService genericService;

    public GenericController(GenericService genericService) {
        this.genericService = genericService;
    }
}
