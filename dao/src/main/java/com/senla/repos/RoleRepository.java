package com.senla.repos;

import com.senla.entity.Role;

import java.util.List;

public interface RoleRepository extends CommonRepository<Role> {


    List<Role> findByName(String name);
}
