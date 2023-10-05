package com.paul.billing_system.controller;

import com.paul.billing_system.dto.DrugOrderDTO;
import com.paul.billing_system.service.DrugOrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugOrders")
@PreAuthorize("hasAnyAuthority('ROLE_ORG_ADMIN','ROLE_ADMIN','ROLE_ROOT')")
public class DrugOrderController {
    private final DrugOrderService drugOrderService;

    public DrugOrderController(DrugOrderService drugOrderService) {
        this.drugOrderService = drugOrderService;
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<?> saveOrder(@Valid @RequestBody DrugOrderDTO drugOrderDTO) {
        return new ResponseEntity<>(drugOrderService.saveOrder(drugOrderDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAllDrugOrders")
    public List<DrugOrderDTO> getAllDrugOrders(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return drugOrderService.getAllOrders(PageRequest.of(page, size));
    }

    @GetMapping("/getAllDrugOrdersByOrgId/{orgId}")
    public List<DrugOrderDTO> getAllDrugOrdersByOrgId(@PathVariable Long orgId,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return drugOrderService.getAllOrdersByOrgId(orgId, PageRequest.of(page, size));
    }

    @GetMapping("/getDrugOrderById/{id}")
    public DrugOrderDTO getDrugOrderById(@PathVariable Long id) {
        return drugOrderService.getOrder(id);
    }

    @GetMapping("/search/{orgId}/{patientName}")
    public ResponseEntity<?> searchDrugOrder(@PathVariable Long orgId,
                                             @PathVariable String patientName,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(drugOrderService.searchDrugOrder(orgId, patientName, PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/countDrugOrders/{orgId}")
    public Long countDrugOrders(@PathVariable Long orgId){
        return drugOrderService.countDrugOrders(orgId);
    }
}
