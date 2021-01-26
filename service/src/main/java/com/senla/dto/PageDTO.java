package com.senla.dto;

import lombok.Data;
import java.util.ArrayList;

@Data
public class PageDTO extends AbstractDTO{  //Pagination

    private ArrayList<AbstractDTO> list;
    private Integer totalPages;


    @Override
    public int getDtoId() {
        return 0;
    }

    @Override
    public void setDtoId(int dtoId) {

    }
}
