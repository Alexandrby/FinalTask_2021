package com.senla.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class ProfileDTO extends AbstractDTO{

    private Integer profileId;
    private String firstName;
    private String secondName;
    private Integer discountId;
    private Integer userId;


    @Override
    public int getDtoId() {
        return profileId;
    }

    @Override
    public void setDtoId(int dtoId) {

    }
}