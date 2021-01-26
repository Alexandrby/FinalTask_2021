package servicestests;

import com.senla.dto.RentPointDTO;
import com.senla.entity.*;
import com.senla.mapper.RentPointMapper;
import com.senla.repos.RentPointRepository;
import com.senla.services.RentPointService;
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
public class RentPointServiceTest implements CRUDTest {

    RentPointMapper rentPointMapper = mock(RentPointMapper.class);
    RentPointRepository rentPointRepository = mock(RentPointRepository.class);

    RentPoint rentPoint = new RentPoint();
    RentPointDTO rentPointDTO = new RentPointDTO();

    RentPointService service;

    @Test
    public void findByIdTest() {
        RentPointService service = getService();
        when(rentPointMapper.toDto(any())).thenReturn(rentPointDTO);
        when(rentPointRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(rentPoint));
        RentPointDTO rentPointDTO = service.getOne(1);
        assertEquals(rentPointDTO, this.rentPointDTO);
        verify(rentPointRepository).findById(1);
    }

    @Test
    @Override
    public void findAllTest() {
        RentPointService service = getService();
        List<RentPoint> rentPoints = new ArrayList<>();
        rentPoints.add(rentPoint);
        Page page = new PageImpl(rentPoints);
        when(rentPointMapper.toDto(any())).thenReturn(rentPointDTO);
        when(rentPointRepository.findAll(PageRequest.of(1, 10))).thenReturn(page);
        service.getAll(1);
        verify(rentPointRepository).findAll(PageRequest.of(1, 10));
    }

    @Test
    @Override
    public void saveTest() {
        RentPointService service = getService();
        rentPointDTO.setCityId(1);
        when(rentPointMapper.toEntity(any())).thenReturn(rentPoint);
        service.save(rentPointDTO);
        verify(rentPointRepository).save(any());
    }


    @Test
    @Override
    public void updateTest() {
        RentPointService service = getService();
        rentPointDTO.setCityId(1);
        when(rentPointRepository.existsById(0)).thenReturn(true);
        when(rentPointMapper.toEntity(any())).thenReturn(rentPoint);
        service.update(rentPointDTO);
        verify(rentPointRepository).save(any());
    }

    @Test
    @Override
    public void deleteTest() {
        RentPointService service = getService();
        when(rentPointRepository.existsById(1)).thenReturn(true);
        service.deleteById(1);
        verify(rentPointRepository).deleteById(1);
    }

    @PostConstruct
    public RentPointService getService() {
        if (service == null) {
            service = new RentPointService(rentPointRepository,rentPointMapper);
        }
        return service;
    }
}
