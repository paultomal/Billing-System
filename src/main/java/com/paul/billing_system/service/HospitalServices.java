package com.paul.billing_system.service;

import com.paul.billing_system.dto.HospitalDTO;
import com.paul.billing_system.entity.Hospital;
import com.paul.billing_system.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServices {
    private final HospitalRepository hospitalRepository;

    public HospitalServices(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital save(HospitalDTO hospitalDTO) {
        Hospital hospital = new Hospital();
        hospital.setName(hospitalDTO.getName());
        return hospitalRepository.save(hospital);
    }

    public List<Hospital> getALlHospitalList() {
        return hospitalRepository.findAll();
    }
}
