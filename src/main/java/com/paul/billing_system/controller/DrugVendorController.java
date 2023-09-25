package com.paul.billing_system.controller;

import com.paul.billing_system.entity.DrugVendor;
import com.paul.billing_system.service.DrugVendorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugVendors")
public class DrugVendorController {
    private final DrugVendorService drugVendorService;

    public DrugVendorController(DrugVendorService drugVendorService) {
        this.drugVendorService = drugVendorService;
    }

    @GetMapping("/searchDrugVendor/{name}")
    public List<DrugVendor> searchDrugVendor(@PathVariable String name,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return drugVendorService.searchDrugVendor(name, PageRequest.of(page, size));
    }
}
