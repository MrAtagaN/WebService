package com.plekhanov.webService.dao;

import com.plekhanov.webService.web.security.Role;
import java.util.Set;


public interface UserRoleDao {

    Set<Role> findRolesByUserId(long userId);

    void addRoleToUser(long userId, Role role);

    void deleteRoleOfUser(long userId, Role role);
}
