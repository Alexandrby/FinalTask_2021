package servicestests;

import com.senla.dto.DiscountDTO;
import com.senla.entity.Discount;
import com.senla.mapper.DiscountMapper;
import com.senla.repos.DiscountRepository;
import com.senla.services.DiscountService;
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
public class DiscountServiceTest implements CRUDTest {

    DiscountMapper discountMapper = mock(DiscountMapper.class);

    Discount discount = new Discount();
    DiscountDTO discountDTO = new DiscountDTO();
    DiscountRepository discountRepository = mock(DiscountRepository.class);

    DiscountService service;

    @Test
    public void findByIdTest() {
        DiscountService service = getService();
        when(discountMapper.toDto(any())).thenReturn(discountDTO);
        when(discountRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(discount));
        DiscountDTO discount1 = service.getOne(1);
        assertEquals(discountDTO, discount1);
        verify(discountRepository).findById(1);
    }

    @Test
    @Override
    public void findAllTest() {
        DiscountService service = getService();
        List<Discount> discountList = new ArrayList<>();
        discountList.add(discount);
        Page page = new PageImpl(discountList);
        when(discountMapper.toDto(any())).thenReturn(discountDTO);
        when(discountRepository.findAll(PageRequest.of(1, 10))).thenReturn(page);
        service.getAll(1);
        verify(discountRepository).findAll(PageRequest.of(1, 10));
    }

    @Test
    @Override
    public void saveTest() {
        DiscountService service = getService();
        //discountDTO.setProfileId(1);
        when(discountMapper.toEntity(any())).thenReturn(discount);
        service.save(discountDTO);
        verify(discountRepository).save(any());
    }

    @Test
    @Override
    public void updateTest() {
        DiscountService service = getService();
        //discountDTO.setProfileId(1);
        discountDTO.setDiscountId(1);
        discountDTO.setDtoId(1);
        when(discountRepository.existsById(1)).thenReturn(true);
        when(discountMapper.toEntity(discountDTO)).thenReturn(discount);
        service.update(discountDTO);
        verify(discountRepository).save(any(Discount.class));
    }

    @Test
    @Override
    public void deleteTest() {
        DiscountService service = getService();
        when(discountRepository.existsById(1)).thenReturn(true);
        service.deleteById(1);
        verify(discountRepository).deleteById(1);
    }

    @PostConstruct
    public DiscountService getService(){
        if  (service == null){
            service = new DiscountService(discountRepository, discountMapper);
        }
        return service;
    }
}
