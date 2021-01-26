package com.senla.dto;

import lombok.Data;

@Data
public class ScooterDTO extends AbstractDTO{

    private Integer scooterId;
    private String model;
    private String status;
    private Integer rentPointId;

    @Override
    public int getDtoId() {
        return scooterId;
    }

    @Override
    public void setDtoId(int dtoId) {

    }
}
