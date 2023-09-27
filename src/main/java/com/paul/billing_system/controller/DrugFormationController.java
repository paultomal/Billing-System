package com.paul.billing_system.controller;

import com.paul.billing_system.entity.DrugFormation;
import com.paul.billing_system.service.DrugFormationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugFormations")
public class DrugFormationController {
    private final DrugFormationService drugFormationService;

    public DrugFormationController(DrugFormationService drugFormationService) {
        this.drugFormationService = drugFormationService;
    }

    @GetMapping("/searchDrugFormation/{name}")
    public List<DrugFormation> searchDrugFormation(@PathVariable String name,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return drugFormationService.searchDrugFormation(name, PageRequest.of(page, size));
    }
}
