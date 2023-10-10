package com.paul.billing_system.service;

import com.paul.billing_system.dto.DrugDTO;
import com.paul.billing_system.dto.DrugOrderDTO;
import com.paul.billing_system.entity.DrugOrder;
import com.paul.billing_system.entity.OrgDrugPriceQuantity;
import com.paul.billing_system.entity.Patient;
import com.paul.billing_system.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrugOrderService {
    private final DrugOrderRepository drugOrderRepository;
    private final OrganizationRepository organizationRepository;
    private final DrugRepository drugRepository;
    private final PatientRepository patientRepository;
    private final OrgDrugPriceQuantityRepository priceQuantityRepository;

    public DrugOrderService(DrugOrderRepository drugOrderRepository, OrganizationRepository organizationRepository, DrugRepository drugRepository, PatientRepository patientRepository, OrgDrugPriceQuantityRepository priceQuantityRepository) {
        this.drugOrderRepository = drugOrderRepository;
        this.organizationRepository = organizationRepository;
        this.drugRepository = drugRepository;
        this.patientRepository = patientRepository;
        this.priceQuantityRepository = priceQuantityRepository;
    }

    @Transactional
    public DrugOrderDTO saveOrder(DrugOrderDTO drugOrderDTO) {
        DrugOrder drugOrder = new DrugOrder();

        if (drugOrderDTO.getPatientId() != null && patientRepository.findById(drugOrderDTO.getPatientId()).isPresent()) {
            drugOrder.setPatient(patientRepository.findById(drugOrderDTO.getPatientId()).get());
        } else {
            Patient patient = new Patient();
            patient.setName(drugOrderDTO.getPatientName());
            patient.setContact(drugOrderDTO.getPatientContact());
            patient.setOrganization(organizationRepository.findById(drugOrderDTO.getOrgId()).orElseThrow());

            drugOrder.setPatient(patientRepository.save(patient));
        }

        drugOrder.setOrganization(organizationRepository.findById(drugOrderDTO.getOrgId()).orElseThrow());

        List<DrugDTO> availableDrugs = new ArrayList<>();
        for (DrugDTO drug : drugOrderDTO.getDrugList()) {
            OrgDrugPriceQuantity drugPriceQuantity = priceQuantityRepository.findByOrganizationIdAndDrugId(drugOrderDTO.getOrgId(), drug.getId());
            if (drugPriceQuantity != null) {
                if (drugPriceQuantity.getQuantity() != null && drugPriceQuantity.getQuantity() > 0) {
                    drug.setPrice(drugPriceQuantity.getPrice());
                    availableDrugs.add(drug);
                    drugPriceQuantity.setQuantity(drugPriceQuantity.getQuantity() - 1);
                }
            }
        }
        drugOrder.setDrugList(availableDrugs
                .stream()
                .map(drugDTO -> drugRepository.findById(drugDTO.getId()).orElse(null))
                .toList());

        List<Double> priceList = availableDrugs.stream().map(DrugDTO::getPrice).toList();
        Double total = priceList.stream().reduce(0.0, Double::sum);
        drugOrder.setTotal(total);

        return form(drugOrderRepository.save(drugOrder));
    }

    public List<DrugOrderDTO> getAllOrders(PageRequest pageRequest) {
        return drugOrderRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(this::form)
                .toList();
    }

    public DrugOrderDTO getOrder(Long id) {
        return drugOrderRepository.findById(id)
                .map(this::form)
                .orElse(null);
    }

    public List<DrugOrderDTO> getAllOrdersByOrgId(Long orgId, PageRequest pageRequest) {
        return drugOrderRepository.getDrugOrdersByOrganizationId(orgId, pageRequest)
                .stream()
                .map(this::form)
                .toList();
    }

    public List<DrugOrderDTO> searchDrugOrder(Long orgId, String patientName, PageRequest pageRequest) {
        return drugOrderRepository.findByOrganizationAndPatient(orgId, patientName, pageRequest)
                .stream()
                .map(this::form)
                .toList();
    }

    public DrugOrderDTO form(DrugOrder drugOrder) {
        DrugOrderDTO drugOrderDTO = new DrugOrderDTO();
        drugOrderDTO.setId(drugOrder.getId());
        drugOrderDTO.setPatientId(drugOrder.getPatient().getId());
        drugOrderDTO.setPatientName(drugOrder.getPatient().getName());
        drugOrderDTO.setPatientContact(drugOrder.getPatient().getContact());
        drugOrderDTO.setOrgId(drugOrder.getOrganization().getId());
        drugOrderDTO.setDrugList(drugOrder.getDrugList().stream().map(DrugDTO::form).toList().stream()
                .peek(drugDTO -> {
                    OrgDrugPriceQuantity orgDrugPriceQuantity = priceQuantityRepository.findByOrganizationIdAndDrugId(drugOrder.getOrganization().getId(), drugDTO.getId());
                    if (orgDrugPriceQuantity != null) {
                        if (orgDrugPriceQuantity.getPrice() != null) {
                            drugDTO.setPrice(orgDrugPriceQuantity.getPrice());
                        }
                    }
                })
                .toList());
        drugOrderDTO.setTotal(drugOrder.getTotal());

        return drugOrderDTO;
    }

    public Long countDrugOrders(Long orgId) {
        return (long) drugOrderRepository.findAllByOrganizationId(orgId).size();
    }
}
