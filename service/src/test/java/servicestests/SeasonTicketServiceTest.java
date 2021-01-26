package servicestests;

import com.senla.dto.SeasonTicketDTO;
import com.senla.entity.SeasonTicket;
import com.senla.mapper.SeasonTicketMapper;
import com.senla.repos.SeasonTicketRepository;
import com.senla.services.SeasonTicketService;
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
public class SeasonTicketServiceTest implements CRUDTest {

    SeasonTicketMapper seasonTicketMapper = mock(SeasonTicketMapper.class);
    SeasonTicketRepository seasonTicketRepository = mock(SeasonTicketRepository.class);

    SeasonTicket seasonTicket = new SeasonTicket();
    SeasonTicketDTO seasonTicketDTO = new SeasonTicketDTO();

    SeasonTicketService service;

    @Test
    public void findByIdTest() {
        SeasonTicketService service = getService();
        when(seasonTicketMapper.toDto(any())).thenReturn(seasonTicketDTO);
        when(seasonTicketRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(seasonTicket));
        SeasonTicketDTO seasonTicketDTO = service.getOne(1);
        assertEquals(seasonTicketDTO, this.seasonTicketDTO);
        verify(seasonTicketRepository).findById(1);
    }

    @Test
    @Override
    public void findAllTest() {
        SeasonTicketService service = getService();
        List<SeasonTicket> seasonTickets = new ArrayList<>();
        seasonTickets.add(seasonTicket);
        Page page = new PageImpl(seasonTickets);
        when(seasonTicketMapper.toDto(any())).thenReturn(seasonTicketDTO);
        when(seasonTicketRepository.findAll(PageRequest.of(1, 10))).thenReturn(page);
        service.getAll(1);
        verify(seasonTicketRepository).findAll(PageRequest.of(1, 10));
    }

    @Test
    @Override
    public void saveTest() {
        SeasonTicketService service = getService();
        seasonTicketDTO.setProfileId(1);
        when(seasonTicketMapper.toEntity(any())).thenReturn(seasonTicket);
        service.save(seasonTicketDTO);
        verify(seasonTicketRepository).save(any());
    }

    @Test
    @Override
    public void updateTest() {
        SeasonTicketService service = getService();
        seasonTicketDTO.setProfileId(1);
        when(seasonTicketRepository.existsById(0)).thenReturn(true);
        when(seasonTicketMapper.toEntity(any())).thenReturn(seasonTicket);
        service.update(seasonTicketDTO);
        verify(seasonTicketRepository).save(any());
    }

    @Test
    @Override
    public void deleteTest() {
        SeasonTicketService service = getService();
        when(seasonTicketRepository.existsById(1)).thenReturn(true);
        service.deleteById(1);
        verify(seasonTicketRepository).deleteById(1);
    }

    @PostConstruct
    public SeasonTicketService getService() {
        if (service == null) {
            service = new SeasonTicketService(seasonTicketRepository, seasonTicketMapper);
        }
        return service;
    }
}
