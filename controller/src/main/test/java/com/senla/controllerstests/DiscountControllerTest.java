package com.senla.controllerstests;

import com.senla.controllers.DiscountController;
import com.senla.dto.DiscountDTO;
import com.senla.dto.PageDTO;

import com.senla.services.DiscountService;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DiscountControllerTest implements CRUDTest {

    DiscountService service = mock(DiscountService.class);
    DiscountController discountController = new DiscountController(service);
    DiscountDTO discountDTO = new DiscountDTO();

    @Test
    public void findByIdTest(){
        when(service.getOne(1)).thenReturn(discountDTO);
        assertEquals(HttpStatus.OK, discountController.getDiscountById(1).getStatusCode());
    }

    @Test
    public void findAllTest(){
        when(service.getAll(0)).thenReturn(new PageDTO());
        discountController.getAllDiscount(0);
        assertEquals(HttpStatus.OK, discountController.getAllDiscount(1).getStatusCode());
        verify(service).getAll(0);
    }

    @Test
    @Override
    public void saveTest() {
        when(service.save(discountDTO)).thenReturn(discountDTO);
        assertEquals(HttpStatus.CREATED,  discountController.saveDiscount(discountDTO).getStatusCode());
        verify(service).save(discountDTO);

    }

    @Test
    @Override
    public void updateTest() {
        when(service.update(discountDTO)).thenReturn(discountDTO);
        assertEquals(HttpStatus.CREATED,  discountController.updateDiscount(discountDTO).getStatusCode());

        verify(service).update(discountDTO);
    }

    @Test
    @Override
    public void deleteTest() {
        assertEquals(HttpStatus.OK,  discountController.deleteById(1).getStatusCode());
        verify(service).deleteById(1);
    }

}
