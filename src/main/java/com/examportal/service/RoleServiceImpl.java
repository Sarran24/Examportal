package com.examportal.service;


import com.examportal.model.Role;
import com.examportal.repository.RoleRepository;

public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}
