package servicestests;

import com.senla.dto.RoleDTO;
import com.senla.entity.Role;
import com.senla.mapper.RoleMapper;
import com.senla.repos.RoleRepository;
import com.senla.services.RoleService;
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
public class RoleServiceTest implements CRUDTest {


    RoleMapper modelMapper = mock(RoleMapper.class);
    RoleRepository repository = mock(RoleRepository.class);
    RoleService service;
    Role role = new Role("admin");

    @Test
    public void findByIdTest() {
        RoleDTO dto = new RoleDTO();
        dto.setName("admin");
        RoleService service = getService();
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(role));
        when(modelMapper.toDto(any(Role.class))).thenReturn(dto);
        assertEquals(role.getName(), service.getOne(1).getName());
        verify(repository).findById(1);
    }

    @Test
    @Override
    public void findAllTest() {
        RoleService service = getService();
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Page page = new PageImpl(roles);
        when(repository.findAll(PageRequest.of(1,10))).thenReturn(page);
        service.getAll(1);
        verify(repository).findAll(PageRequest.of(1,10));
    }

    @Override
    public void saveTest() {
        /**
         *  there are no separate method
         */
    }

    @Override
    public void updateTest() {
        /**
         *  there are no separate method
         */
    }

    @Override
    public void deleteTest() {
        /**
         *  there are no separate method
         */
    }

    @PostConstruct
    public RoleService getService() {
        if (service == null) {
            service = new RoleService(repository, modelMapper);
        }
        return service;
    }
}
