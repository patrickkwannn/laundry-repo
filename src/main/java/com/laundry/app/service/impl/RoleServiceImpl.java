package com.laundry.app.service.impl;

import com.laundry.app.entity.Role;
import com.laundry.app.repository.RoleRepository;
import com.laundry.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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

    @EventListener(ApplicationReadyEvent.class)
    public void addBasicRoles(){
        Role role = new Role();
        role.setDescription("role for customer");
        role.setName("USER");
        roleRepository.save(role);

        Role rolex = new Role();
        role.setDescription("role for admins");
        role.setName("ADMIN");
        roleRepository.save(rolex);
    }

}
