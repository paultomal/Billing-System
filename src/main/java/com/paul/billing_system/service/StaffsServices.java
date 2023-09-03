package com.paul.billing_system.service;

import com.paul.billing_system.dto.StaffsDTO;
import com.paul.billing_system.entity.Staffs;
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

    public Staffs saveStaffs(StaffsDTO staffsDTO) {
        Staffs staffs = new Staffs();
        staffs.setName(staffsDTO.getName());
        staffs.setDesignation(staffsDTO.getDesignation());
        return staffsRepository.save(staffs);
    }

    public List<Staffs> getAllStaffs() {
    return staffsRepository.findAll();
    }

    public Staffs updateStaff(StaffsDTO staffsDTO, Long id) {
        Optional<Staffs> staffs = staffsRepository.findById(id);
        if (staffs.isPresent()){
            Staffs staffs1 = new Staffs();
            staffs1.setName(staffsDTO.getName());
            staffs1.setDesignation(staffsDTO.getDesignation());
            return staffsRepository.save(staffs1);
        }
        return new Staffs();
    }
}
