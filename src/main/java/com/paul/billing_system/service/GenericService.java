package com.paul.billing_system.service;

import com.paul.billing_system.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class GenericService {
    private final GenericRepository genericRepository;

    public GenericService(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }
}
