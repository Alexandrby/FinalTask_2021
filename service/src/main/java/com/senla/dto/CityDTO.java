package com.senla.dto;

import lombok.Data;

@Data
public class CityDTO extends AbstractDTO{

    private Integer cityId;
    private String cityName;


    @Override
    public int getDtoId() {
        return cityId;
    }

    @Override
    public void setDtoId(int dtoId) {

    }
}
