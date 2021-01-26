package com.senla.mapper;

import com.senla.dto.RoleDTO;
import com.senla.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;

public class RoleMapper implements MapperAPI<Role, RoleDTO> {

    @Autowired
    private MainMapper modelMapper;

    @Override
    public RoleDTO toDto(Role role) {
      return Objects.isNull(role) ? null : modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        return Objects.isNull(roleDTO) ? null : modelMapper.map(roleDTO, Role.class);
    }
}
