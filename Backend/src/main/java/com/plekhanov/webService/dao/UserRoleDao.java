package com.plekhanov.webService.dao;

import com.plekhanov.webService.web.security.Role;
import java.util.Set;


public interface UserRoleDao {

    Set<Role> findRolesByUserId(long userId);
}
