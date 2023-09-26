package com.paul.billing_system.controller;

import com.paul.billing_system.service.DrugFormationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drugFormations")
public class DrugFormationController {
    private final DrugFormationService drugFormationService;

    public DrugFormationController(DrugFormationService drugFormationService) {
        this.drugFormationService = drugFormationService;
    }
}
