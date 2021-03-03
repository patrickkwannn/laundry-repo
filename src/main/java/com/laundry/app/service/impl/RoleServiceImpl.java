package com.laundry.app.service.impl;

import com.laundry.app.entity.Role;
import com.laundry.app.repository.RoleRepository;
import com.laundry.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addBasicRoles(){
        if(!roleRepository.existsById(1L)) {
            Role role = new Role();
            role.setDescription("role for customer");
            role.setName("USER");
            roleRepository.save(role);
        }

        if(!roleRepository.existsById(2L)) {
            Role rolex = new Role();
            rolex.setDescription("role for admins");
            rolex.setName("ADMIN");
            roleRepository.save(rolex);
        }
    }

}
