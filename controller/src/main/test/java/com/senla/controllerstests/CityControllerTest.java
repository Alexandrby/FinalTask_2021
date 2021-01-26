package com.senla.controllerstests;

import com.senla.controllers.CityController;
import com.senla.dto.CityDTO;
import com.senla.dto.PageDTO;
import com.senla.services.CityService;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CityControllerTest implements CRUDTest {

    CityService service = mock(CityService.class);
    CityController cityController = new CityController(service);
    CityDTO cityDTO = new CityDTO();

    @Test
    public void findByIdTest(){
        when(service.getOne(1)).thenReturn(cityDTO);
        assertEquals(HttpStatus.OK, cityController.getCityById(1).getStatusCode());
    }

    @Test
    public void findAllTest(){
        when(service.getAll(1)).thenReturn(new PageDTO());
        cityController.getAllCities(1);
        verify(service).getAll(1);
    }

    @Test
    @Override
    public void saveTest() {
        when(service.save(cityDTO)).thenReturn(cityDTO);
        assertEquals(HttpStatus.CREATED,  cityController.saveCity(cityDTO).getStatusCode());
        verify(service).save(cityDTO);

    }

    @Test
    @Override
    public void updateTest() {
        when(service.update(cityDTO)).thenReturn(cityDTO);
        assertEquals(HttpStatus.CREATED,  cityController.updateCity(cityDTO).getStatusCode());
        verify(service).update(cityDTO);
    }

    @Test
    @Override
    public void deleteTest() {
        assertEquals(HttpStatus.OK,  cityController.deleteById(1).getStatusCode());
        verify(service).deleteById(1);
    }

}
