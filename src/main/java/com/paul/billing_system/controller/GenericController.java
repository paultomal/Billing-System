package com.paul.billing_system.controller;

import com.paul.billing_system.entity.Generic;
import com.paul.billing_system.service.GenericService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generics")
public class GenericController {
    private final GenericService genericService;

    public GenericController(GenericService genericService) {
        this.genericService = genericService;
    }

    @GetMapping("/searchGeneric/{name}")
    public List<Generic> searchGeneric(@PathVariable String name,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return genericService.searchGeneric(name, PageRequest.of(page, size));
    }
}
