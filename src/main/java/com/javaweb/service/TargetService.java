package com.javaweb.service;

import com.javaweb.domain.Target;
import com.javaweb.repository.TargetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetService {
    private final TargetRepository targetRepository;

    public TargetService(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    public List<Target> handlefindAllTarget() {
        return this.targetRepository.findAll();
    }
}
