package com.senla.dto;

import lombok.Data;

@Data
public class RentPointDTO extends AbstractDTO{

    private Integer rentPointId;
    private String address;
    private String phone;
    private Integer cityId;

    @Override
    public int getDtoId() {
        return rentPointId;
    }

    @Override
    public void setDtoId(int dtoId) {

    }
}
