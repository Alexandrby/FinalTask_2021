package com.senla.services;

import com.senla.dto.RoleDTO;
import com.senla.entity.Role;
import com.senla.mapper.RoleMapper;
import com.senla.repos.RoleRepository;
import com.senla.serviceAPI.AbstractService;

public class RoleService extends AbstractService<Role, RoleDTO, RoleRepository, RoleMapper> {


    public RoleService(RoleRepository repository, RoleMapper mapper) {
        super(repository, mapper);
    }
}
