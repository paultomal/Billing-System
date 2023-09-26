package com.paul.billing_system.controller;

import com.paul.billing_system.service.DrugStrengthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drugStrengths")
public class DrugStrengthController {
    private final DrugStrengthService drugStrengthService;

    public DrugStrengthController(DrugStrengthService drugStrengthService) {
        this.drugStrengthService = drugStrengthService;
    }
}
