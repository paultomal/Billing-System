package com.paul.billing_system.controller;

import com.paul.billing_system.entity.DrugStrength;
import com.paul.billing_system.service.DrugStrengthService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugStrengths")
public class DrugStrengthController {
    private final DrugStrengthService drugStrengthService;

    public DrugStrengthController(DrugStrengthService drugStrengthService) {
        this.drugStrengthService = drugStrengthService;
    }

    @GetMapping("/searchDrugStrength/{name}")
    public List<DrugStrength> searchDrugStrength(@PathVariable String name,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return drugStrengthService.searchDrugStrength(name, PageRequest.of(page, size));
    }
}
