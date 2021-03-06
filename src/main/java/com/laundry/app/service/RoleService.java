package com.laundry.app.service;

import com.laundry.app.entity.Role;

import java.util.List;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
public interface RoleService {
    Role getByRoleName(String roleName);
    Role addRole(String roleName, String description);
    List<Role> getAllRoles();
}
