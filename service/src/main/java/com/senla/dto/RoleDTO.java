package com.senla.dto;

import lombok.Data;

@Data
public class RoleDTO extends AbstractDTO {

    private Integer roleId;
    private String name;

    @Override
    public int getDtoId() {
        return roleId;
    }

    @Override
    public void setDtoId(int dtoId) {

    }
}
