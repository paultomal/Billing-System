package com.paul.billing_system.controller;

import com.paul.billing_system.dto.DrugDTO;
import com.paul.billing_system.dto.OrgDrugPriceQuantityDTO;
import com.paul.billing_system.service.DrugService;
import com.paul.billing_system.service.OrgDrugPriceQuantityService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugs")
@PreAuthorize("hasAnyAuthority('ROLE_ORG_ADMIN','ROLE_ADMIN','ROLE_ROOT')")
public class DrugController {
    private final DrugService drugService;
    private final OrgDrugPriceQuantityService orgDrugPriceQuantityService;

    public DrugController(DrugService drugService, OrgDrugPriceQuantityService orgDrugPriceQuantityService) {
        this.drugService = drugService;
        this.orgDrugPriceQuantityService = orgDrugPriceQuantityService;
    }

    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @PostMapping("/addDrug")
    public ResponseEntity<?> addDrug(@Valid @RequestBody DrugDTO drugDTO) {
        return new ResponseEntity<>(drugService.addDrug(drugDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAllDrugs")
    public List<DrugDTO> getDrugs(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return drugService.getAllDrugs(PageRequest.of(page,size));
    }

    @GetMapping("/getAllDrugs/{orgId}")
    public List<DrugDTO> getDrugsByOrgId(@PathVariable Long orgId,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return drugService.getAllDrugsOfOrg(orgId, PageRequest.of(page,size));
    }

    @GetMapping("/getDrug/{id}")
    public DrugDTO getDrug(@PathVariable Long id) {
        return drugService.getDrugById(id);
    }

    @GetMapping("/getDrug/{id}/{orgId}")
    public DrugDTO getDrugByIdAndOrgId(@PathVariable Long id, @PathVariable Long orgId) {
        return drugService.getDrugOfOrg(id, orgId);
    }

    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @PutMapping("/updateDrug/{id}")
    public DrugDTO updateDrug(@PathVariable Long id, @RequestBody DrugDTO drugDTO) {
        return drugService.updateDrug(id, drugDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
    @PutMapping("/updatePriceAndQuantity")
    public OrgDrugPriceQuantityDTO updatePriceQuantity(@RequestBody OrgDrugPriceQuantityDTO priceQuantityDTO) {
        return orgDrugPriceQuantityService.updatePriceQuantity(priceQuantityDTO);
    }

    @DeleteMapping("/deleteDrug/{id}")
    public String deleteDrug(@PathVariable Long id) {
        return drugService.deleteDrug(id);
    }

    @GetMapping("/searchDrugByBrandName/{brandName}")
    public List<DrugDTO> searchDrugByBrandName(@PathVariable String brandName,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return drugService.searchDrugByBrandName(brandName, PageRequest.of(page, size));
    }

    @GetMapping("/searchDrugByVendor/{vendor}")
    public List<DrugDTO> searchDrugByVendor(@PathVariable String vendor,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return drugService.searchDrugByVendor(vendor, PageRequest.of(page, size));
    }

    @GetMapping("/searchDrugByGeneric/{generic}")
    public List<DrugDTO> searchDrugByGeneric(@PathVariable String generic,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return drugService.searchDrugByGeneric(generic, PageRequest.of(page, size));
    }

    @GetMapping("/searchDrugByFormation/{formation}")
    public List<DrugDTO> searchDrugByFormation(@PathVariable String formation,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return drugService.searchDrugByFormation(formation, PageRequest.of(page, size));
    }

    @GetMapping("/searchDrugByStrength/{strength}")
    public List<DrugDTO> searchDrugByStrength(@PathVariable String strength,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return drugService.searchDrugByStrength(strength, PageRequest.of(page, size));
    }

}
