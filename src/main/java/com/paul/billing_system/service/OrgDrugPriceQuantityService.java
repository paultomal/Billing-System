package com.paul.billing_system.service;

import com.paul.billing_system.dto.OrgDrugPriceQuantityDTO;
import com.paul.billing_system.entity.OrgDrugPriceQuantity;
import com.paul.billing_system.repository.DrugRepository;
import com.paul.billing_system.repository.OrgDrugPriceQuantityRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrgDrugPriceQuantityService {
    private final OrgDrugPriceQuantityRepository orgDrugPriceQuantityRepository;
    private final OrganizationRepository organizationRepository;
    private final DrugRepository drugRepository;

    public OrgDrugPriceQuantityService(OrgDrugPriceQuantityRepository orgDrugPriceQuantityRepository, OrganizationRepository organizationRepository, DrugRepository drugRepository) {
        this.orgDrugPriceQuantityRepository = orgDrugPriceQuantityRepository;
        this.organizationRepository = organizationRepository;
        this.drugRepository = drugRepository;
    }

    @Transactional
    public OrgDrugPriceQuantityDTO updatePriceQuantity(OrgDrugPriceQuantityDTO priceQuantityDTO) {
        OrgDrugPriceQuantity orgDrugPriceQuantity = orgDrugPriceQuantityRepository.findByOrganizationIdAndDrugId(priceQuantityDTO.getOrgId(), priceQuantityDTO.getDrugId());

        if(orgDrugPriceQuantity == null) {
            orgDrugPriceQuantity = new OrgDrugPriceQuantity();
        }
        orgDrugPriceQuantity.setOrganization(organizationRepository.findById(priceQuantityDTO.getOrgId()).orElseThrow());
        orgDrugPriceQuantity.setDrug(drugRepository.findById(priceQuantityDTO.getDrugId()).orElseThrow());
        orgDrugPriceQuantity.setPrice(priceQuantityDTO.getPrice());
        orgDrugPriceQuantity.setQuantity(priceQuantityDTO.getQuantity());
        orgDrugPriceQuantity.setUpdatedAt(new Date());

        return OrgDrugPriceQuantityDTO.form(orgDrugPriceQuantityRepository.save(orgDrugPriceQuantity));
    }
}
