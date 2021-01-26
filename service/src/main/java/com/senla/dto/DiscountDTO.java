package com.senla.dto;

import lombok.Data;

@Data
public class DiscountDTO extends AbstractDTO{

    private Integer discountId;
    private Integer discountRate;

    @Override
    public int getDtoId() {
        return discountId;
    }

    @Override
    public void setDtoId(int dtoId) {

    }
    //private Integer profileId;

}
