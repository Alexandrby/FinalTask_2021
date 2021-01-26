package servicestests;


import com.senla.dto.RentStoryDTO;
import com.senla.entity.*;
import com.senla.mapper.RentStoryMapper;
import com.senla.repos.RentStoryRepository;
import com.senla.services.RentStoryService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RentStoryServiceTest implements CRUDTest {

    RentStoryMapper rentStoryMapper = mock(RentStoryMapper.class);
    RentStoryRepository rentStoryRepository = mock(RentStoryRepository.class);

    RentStory rentStory = new RentStory();
    RentStoryDTO rentStoryDTO = new RentStoryDTO();

    RentStoryService service;

    @Test
    public void findByIdTest() {
        RentStoryService service = getService();
        when(rentStoryMapper.toDto(any())).thenReturn(rentStoryDTO);
        when(rentStoryRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(rentStory));
        RentStoryDTO rentStoryDTO = service.getOne(1);
        assertEquals(rentStoryDTO, this.rentStoryDTO);
        verify(rentStoryRepository).findById(1);
    }

    @Test
    @Override
    public void findAllTest() {
        RentStoryService service = getService();
        List<RentStory> rentStories = new ArrayList<>();
        rentStories.add(rentStory);
        Page page = new PageImpl(rentStories);
        when(rentStoryMapper.toDto(any())).thenReturn(rentStoryDTO);
        when(rentStoryRepository.findAll(PageRequest.of(1, 10))).thenReturn(page);
        service.getAll(1);
        verify(rentStoryRepository).findAll(PageRequest.of(1, 10));
    }

    @Test
    @Override
    public void saveTest() {
        RentStoryService service = getService();
        rentStoryDTO.setProfileId(1);
        rentStoryDTO.setScooterId(1);
        when(rentStoryMapper.toEntity(any())).thenReturn(rentStory);
        service.save(rentStoryDTO);
        verify(rentStoryRepository).save(any());
    }

    @Test
    @Override
    public void updateTest() {
        RentStoryService service = getService();
        rentStoryDTO.setProfileId(1);
        rentStoryDTO.setScooterId(1);
        when(rentStoryRepository.existsById(0)).thenReturn(true);
        when(rentStoryMapper.toEntity(any())).thenReturn(rentStory);
        service.update(rentStoryDTO);
        verify(rentStoryRepository).save(any());
    }

    @Test
    @Override
    public void deleteTest() {
        RentStoryService service = getService();
        when(rentStoryRepository.existsById(1)).thenReturn(true);
        service.deleteById(1);
        verify(rentStoryRepository).deleteById(1);
    }

    @PostConstruct
    public RentStoryService getService() {
        if (service == null) {
            service = new RentStoryService(rentStoryRepository, rentStoryMapper);
        }
        return service;
    }
}
