package com.paul.billing_system.service;

import com.paul.billing_system.dto.CompoundersDTO;
import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.repository.StaffsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffsServices {
    private final StaffsRepository staffsRepository;

    public StaffsServices(StaffsRepository staffsRepository) {
        this.staffsRepository = staffsRepository;
    }

    public Compounders saveStaffs(CompoundersDTO staffsDTO) {
        Compounders staffs = new Compounders();
        staffs.setName(staffsDTO.getName());
        staffs.setDesignation(staffsDTO.getDesignation());
        return staffsRepository.save(staffs);
    }

    public List<Compounders> getAllStaffs() {
    return staffsRepository.findAll();
    }

    public Compounders updateStaff(CompoundersDTO staffsDTO, Long id) {
        Optional<Compounders> staffs = staffsRepository.findById(id);
        if (staffs.isPresent()){
            Compounders staffs1 = new Compounders();
            staffs1.setName(staffsDTO.getName());
            staffs1.setDesignation(staffsDTO.getDesignation());
            return staffsRepository.save(staffs1);
        }
        return new Compounders();
    }
}
