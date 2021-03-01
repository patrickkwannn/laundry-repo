package com.laundry.app.service.impl;

import com.laundry.app.entity.Role;
import com.laundry.app.repository.RoleRepository;
import com.laundry.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public Role addRole(String roleName, String description) {
        Role role = new Role();
        role.setName(roleName);
        role.setDescription(description);

        return roleRepository.save(role);
    }

}
