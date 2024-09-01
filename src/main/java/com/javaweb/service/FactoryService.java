package com.javaweb.service;

import com.javaweb.domain.Factory;
import com.javaweb.repository.FactoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryService {
    private final FactoryRepository factoryRepository;

    public FactoryService(FactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }

    public List<Factory> handlefindAllFactory() {
        return this.factoryRepository.findAll();
    }
}
