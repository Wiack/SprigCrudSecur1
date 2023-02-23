package com.example.SprigCrudSecur1.service;

import com.example.SprigCrudSecur1.model.Role;
import com.example.SprigCrudSecur1.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRoleAtStartup(Long id, String name) {
        Role role = new Role(id, name);
        roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll(){
        return roleRepository.findAll();
    }


}
