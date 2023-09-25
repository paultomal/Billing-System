package com.paul.billing_system.controller;

import com.paul.billing_system.dto.DrugDTO;
import com.paul.billing_system.service.DrugService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugs")
public class DrugController {
    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @PostMapping("/add/drug")
    public DrugDTO addDrug(@RequestBody DrugDTO drugDTO) {
        return drugService.addDrug(drugDTO);
    }

    @GetMapping("/getAllDrugs")
    public List<DrugDTO> getDrugs(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return drugService.getAllDrugs(PageRequest.of(page,size));
    }

    @GetMapping("/getDrug/{id}")
    public DrugDTO getDrug(@PathVariable Long id) {
        return drugService.getDrugById(id);
    }

    @PutMapping("/updateDrug/{id}")
    public DrugDTO updateDrug(@PathVariable Long id, @RequestBody DrugDTO drugDTO) {
        return drugService.updateDrug(id, drugDTO);
    }

    @DeleteMapping("/deleteDrug/{id}")
    public String deleteDrug(@PathVariable Long id) {
        return drugService.deleteDrug(id);
    }

    @GetMapping("/searchDrug/{name}")
    public List<DrugDTO> searchDrug(@PathVariable String name,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        return drugService.searchDrug(name, PageRequest.of(page, size));
    }

}
