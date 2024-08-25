package com.javaweb.service;

import com.javaweb.domain.Role;
import com.javaweb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String roleName) {
        return this.roleRepository.findByName(roleName);
    }
}
