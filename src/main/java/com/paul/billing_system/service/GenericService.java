package com.paul.billing_system.service;

import com.paul.billing_system.entity.Generic;
import com.paul.billing_system.repository.GenericRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericService {
    private final GenericRepository genericRepository;

    public GenericService(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    public List<Generic> searchGeneric(String name, PageRequest pageRequest) {
        return genericRepository.searchByName(name, pageRequest);
    }
}
