package com.senla.controllerstests;

import com.senla.controllers.ScooterController;
import com.senla.dto.PageDTO;
import com.senla.dto.ScooterDTO;
import com.senla.services.ScooterService;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScooterControllerTest implements CRUDTest {

    ScooterService service = mock(ScooterService.class);
    ScooterController controller = new ScooterController(service);
    ScooterDTO dto = new ScooterDTO();

    @Test
    public void findByIdTest(){
        when(service.getOne(1)).thenReturn(dto);
        assertEquals(HttpStatus.OK, controller.getScooterById(1).getStatusCode());
    }

    @Test
    public void findAllTest(){
        when(service.getAll(1)).thenReturn(new PageDTO());
        assertEquals(HttpStatus.OK, controller.getAllScooters(1).getStatusCode());
        verify(service).getAll(1);
    }

    @Test
    @Override
    public void saveTest() {
        when(service.save(dto)).thenReturn(dto);
        assertEquals(HttpStatus.CREATED,  controller.saveScooter(dto).getStatusCode());
        verify(service).save(dto);
    }

    @Test
    @Override
    public void updateTest() {
        when(service.update(dto)).thenReturn(dto);
        assertEquals(HttpStatus.CREATED,  controller.updateScooter(dto).getStatusCode());
        verify(service).update(dto);
    }

    @Test
    @Override
    public void deleteTest() {
        assertEquals(HttpStatus.OK,  controller.deleteById(1).getStatusCode());
        verify(service).deleteById(1);
    }

}
