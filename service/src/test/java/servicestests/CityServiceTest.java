package servicestests;

import com.senla.dto.CityDTO;
import com.senla.entity.City;
import com.senla.mapper.CityMapper;
import com.senla.repos.CityRepository;
import com.senla.services.CityService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import javax.annotation.PostConstruct;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CityServiceTest implements CRUDTest {

    CityMapper modelMapper = mock(CityMapper.class);
    CityRepository repository = mock(CityRepository.class);
    CityService service;
    City city = new City();

    CityDTO cityDTO = new CityDTO();

    @Test
    public void findByIdTest() {
        CityService service = getService();
        cityDTO.setCityName("Brest");
        city.setCityName("Brest");
        when(modelMapper.toDto(any(City.class))).thenReturn(cityDTO);
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(city));
        assertEquals(city.getCityName(), service.getOne(1).getCityName());
    }

    @Test
    @Override
    public void findAllTest() {
        CityService service = getService();
        ArrayList<City> list = new ArrayList();
        list.add(city);
        CityDTO  cityDTO = new CityDTO();
        cityDTO.setCityName("asd");
        Page<City> cityList = new PageImpl<>(list);
        when(repository.findAll(PageRequest.of(1, 10))).thenReturn(cityList);
        when(modelMapper.toDto(any(City.class))).thenReturn(cityDTO);
        service.getAll(1);
        verify(repository).findAll(PageRequest.of(1, 10));
    }

    @Test
    @Override
    public void saveTest() {
        CityDTO dto = new CityDTO();
        dto.setCityId(1);
        dto.setCityName("abra");
        when(modelMapper.toEntity(dto)).thenReturn(new City());
       CityService service = getService();
       service.save(dto);
       verify(repository).save(any(City.class));
    }

    @Test
    @Override
    public void updateTest() {
        CityDTO dto = new CityDTO();
        dto.setCityId(1);
        dto.setCityName("abra");
        dto.setDtoId(1);
        when(modelMapper.toEntity(dto)).thenReturn(new City());
        when(repository.existsById(1)).thenReturn(true);
        CityService service = getService();
        service.update(dto);
        verify(repository).save(any(City.class));
    }

    @Test
    @Override
    public void deleteTest() {
        CityService service = getService();;
        when(repository.existsById(1)).thenReturn(true);
        service.deleteById(1);
        verify(repository).deleteById(1);
    }

    @PostConstruct
    public CityService getService(){
        if  (service == null){
            service = new CityService(repository, modelMapper);
        }
        return service;
    }

}
