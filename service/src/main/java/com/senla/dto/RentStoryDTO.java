package com.senla.dto;

import lombok.Data;

@Data
public class RentStoryDTO extends AbstractDTO{

    private Integer rentStoryId;
    private Integer rentDuration;
    private Integer Money;
    private String paidWay;
    private Integer profileId;
    private Integer scooterId;


    @Override
    public int getDtoId() {
        return rentStoryId;
    }

    @Override
    public void setDtoId(int dtoId) {

    }
}
