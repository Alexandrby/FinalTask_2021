package servicestests;

import com.senla.dto.ScooterDTO;
import com.senla.entity.Scooter;
import com.senla.mapper.ScooterMapper;
import com.senla.repos.ScooterRepository;
import com.senla.services.ScooterService;
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
public class ScooterServiceTest implements CRUDTest {

    ScooterMapper scooterMapper = mock(ScooterMapper.class);
    ScooterRepository scooterRepository = mock(ScooterRepository.class);
    Scooter scooter = new Scooter(/*"super"*/);
    ScooterDTO scooterDTO = new ScooterDTO();
    ScooterService service;

    @Test
    public void findByIdTest() {
        ScooterService service = getService();
        when(scooterMapper.toDto(any())).thenReturn(scooterDTO);
        when(scooterRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(scooter));
        ScooterDTO scooterDTO = service.getOne(1);
        assertEquals(scooterDTO, this.scooterDTO);
        verify(scooterRepository).findById(1);
    }

    @Test
    @Override
    public void findAllTest() {
        ScooterService service = getService();
        List<Scooter> scooters = new ArrayList<>();
        scooters.add(scooter);
        Page page = new PageImpl(scooters);
        when(scooterMapper.toDto(any())).thenReturn(scooterDTO);
        when(scooterRepository.findAll(PageRequest.of(1,10))).thenReturn(page);
        service.getAll(1);
        verify(scooterRepository).findAll(PageRequest.of(1,10));
    }

    @Test
    @Override
    public void saveTest() {
        ScooterService service = getService();
        scooterDTO.setRentPointId(1);
        when(scooterMapper.toEntity(scooterDTO)).thenReturn(scooter);
        service.save(scooterDTO);
        verify(scooterRepository).save(any(Scooter.class));
    }

    @Test
    @Override
    public void updateTest() {
        ScooterService service = getService();
        scooterDTO.setRentPointId(1);
        when(scooterRepository.existsById(0)).thenReturn(true);
        when(scooterMapper.toEntity(scooterDTO)).thenReturn(scooter);
        service.update(scooterDTO);
        verify(scooterRepository).save(any(Scooter.class));
    }

    @Test
    @Override
    public void deleteTest() {
        ScooterService service = getService();
        when(scooterRepository.existsById(1)).thenReturn(true);
        service.deleteById(1);
        verify(scooterRepository).deleteById(1);
    }

    @PostConstruct
    public ScooterService getService() {
        if (service == null) {
            service = new ScooterService(scooterRepository, scooterMapper);
        }
        return service;
    }
}
