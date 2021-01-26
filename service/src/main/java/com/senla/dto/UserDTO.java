package com.senla.dto;

import lombok.Data;

@Data
public class UserDTO extends AbstractDTO{

    private Integer userId;
    private String login;
    private String password;
    private String role;
    private Integer profileId;

    @Override
    public int getDtoId() {
        return userId;
    }

    @Override
    public void setDtoId(int dtoId) {

    }
}
