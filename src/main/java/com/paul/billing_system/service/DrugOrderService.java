package com.paul.billing_system.service;

import com.paul.billing_system.dto.DrugOrderDTO;
import com.paul.billing_system.entity.DrugOrder;
import com.paul.billing_system.repository.DrugOrderRepository;
import com.paul.billing_system.repository.DrugRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugOrderService {
    private final DrugOrderRepository drugOrderRepository;
    private final OrganizationRepository organizationRepository;
    private final DrugRepository drugRepository;

    public DrugOrderService(DrugOrderRepository drugOrderRepository, OrganizationRepository organizationRepository, DrugRepository drugRepository) {
        this.drugOrderRepository = drugOrderRepository;
        this.organizationRepository = organizationRepository;
        this.drugRepository = drugRepository;
    }

    @Transactional
    public DrugOrderDTO saveOrder(DrugOrderDTO drugOrderDTO) {
        DrugOrder drugOrder = new DrugOrder();
        drugOrder.setPatientName(drugOrderDTO.getPatientName());
        drugOrder.setPatientContact(drugOrderDTO.getPatientContact());
        drugOrder.setOrganization(organizationRepository.findById(drugOrderDTO.getOrgId()).orElseThrow());
        drugOrder.setDrugList(drugOrderDTO.getDrugList()
                .stream()
                .map(drugDTO -> drugRepository.findById(drugDTO.getId()).orElse(null))
                .toList());
        drugOrder.setTotal(drugOrderDTO.getTotal());

        return DrugOrderDTO.form(drugOrderRepository.save(drugOrder));
    }

    public List<DrugOrderDTO> getAllOrders(PageRequest pageRequest) {
        return drugOrderRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(DrugOrderDTO::form)
                .toList();
    }

    public DrugOrderDTO getOrder(Long id) {
        return drugOrderRepository.findById(id)
                .map(DrugOrderDTO::form)
                .orElse(null);
    }

    public List<DrugOrderDTO> getAllOrdersByOrgId(Long orgId, PageRequest pageRequest) {
        return drugOrderRepository.getDrugOrdersByOrganizationId(orgId, pageRequest)
                .stream()
                .map(DrugOrderDTO::form)
                .toList();
    }
}
