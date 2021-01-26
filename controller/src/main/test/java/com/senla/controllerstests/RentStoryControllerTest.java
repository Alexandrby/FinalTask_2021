package com.senla.controllerstests;

import com.senla.controllers.RentStoryController;
import com.senla.dto.PageDTO;
import com.senla.dto.RentStoryDTO;
import com.senla.services.RentStoryService;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.*;

public class RentStoryControllerTest implements CRUDTest {

    RentStoryService service = mock(RentStoryService.class);
    RentStoryController controller = new RentStoryController(service);
    RentStoryDTO dto = new RentStoryDTO();

    @Test
    public void findByIdTest(){
        when(service.getOne(1)).thenReturn(dto);
        assertEquals(HttpStatus.OK, controller.getRentStoryById(1).getStatusCode());
    }

    private void assertEquals(HttpStatus ok, HttpStatus statusCode) {
    }

    @Test
    public void findAllTest(){
        when(service.getAll(1)).thenReturn(new PageDTO());
        assertEquals(HttpStatus.OK, controller.getAllRentStories(1).getStatusCode());
        verify(service).getAll(1);
    }

    @Test
    @Override
    public void saveTest() {
        when(service.save(dto)).thenReturn(dto);
        assertEquals(HttpStatus.CREATED,  controller.saveRentStory(dto).getStatusCode());
        verify(service).save(dto);

    }

    @Test
    @Override
    public void updateTest() {
        when(service.update(dto)).thenReturn(dto);
        assertEquals(HttpStatus.OK,  controller.updateRentStory(dto).getStatusCode());
        verify(service).update(dto);
    }

    @Test
    @Override
    public void deleteTest() {
        assertEquals(HttpStatus.OK,  controller.deleteRentStoryById(1).getStatusCode());
        verify(service).deleteById(1);
    }

}
