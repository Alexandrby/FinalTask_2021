package com.senla.dto;

import lombok.Data;

@Data
public class SeasonTicketDTO extends AbstractDTO{

    private Integer seasonTicketId;
    private Integer hoursLeft;
    private Integer costPerHour;
    private Integer profileId;


    @Override
    public int getDtoId() {
        return seasonTicketId;
    }

    @Override
    public void setDtoId(int dtoId) {

    }
}
