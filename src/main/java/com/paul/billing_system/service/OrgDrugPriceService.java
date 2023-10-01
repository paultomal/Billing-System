package com.paul.billing_system.service;

import com.paul.billing_system.repository.OrgDrugPriceQuantityRepository;
import org.springframework.stereotype.Service;

@Service
public class OrgDrugPriceService {
    private final OrgDrugPriceQuantityRepository orgDrugPriceQuantityRepository;

    public OrgDrugPriceService(OrgDrugPriceQuantityRepository orgDrugPriceQuantityRepository) {
        this.orgDrugPriceQuantityRepository = orgDrugPriceQuantityRepository;
    }


}
