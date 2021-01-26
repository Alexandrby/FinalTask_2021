package java.com.senla.servicestests;

import com.senla.dto.UserDTO;
import com.senla.entity.User;
import com.senla.mapper.UserMapper;
import com.senla.repos.UserRepository;
import com.senla.services.UserService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import servicestests.CRUDTest;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserTest implements CRUDTest {

    UserMapper loginDataMapper = mock(UserMapper.class);
    UserRepository repository = mock(UserRepository.class);
    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    User user = new User(/*"login", "password"*/);
    UserDTO loginDataDto = new UserDTO();

    UserService service;

    @Test
    public void findByIdTest() {
        UserService service = getService();
        when(loginDataMapper.toDto(any())).thenReturn(loginDataDto);
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(user));
        UserDTO userDto = service.getOne(1);
        assertEquals(userDto, this.loginDataDto);
        verify(repository).findById(1);
    }

    @Test
    @Override
    public void findAllTest() {
        UserService service = getService();
        List<User> loginDataList = new ArrayList<>();
        loginDataList.add(user);
        Page page = new PageImpl(loginDataList);
        when(loginDataMapper.toDto(any())).thenReturn(loginDataDto);
        when(repository.findAll(PageRequest.of(1, 10))).thenReturn(page);
        service.getAll(1);
        verify(repository).findAll(PageRequest.of(1, 10));
    }

    @Test
    @Override
    public void saveTest() {
        UserService service = getService();
        when(loginDataMapper.toEntity(any())).thenReturn(user);
        service.save(loginDataDto);
        verify(repository).save(any());
    }


    @Test
    @Override
    public void updateTest() {
        UserService service = getService();
        when(repository.existsById(0)).thenReturn(true);
        when(loginDataMapper.toEntity(any())).thenReturn(user);
        service.update(loginDataDto);
        verify(repository).save(any());
    }

    @Test
    @Override
    public void deleteTest() {
        UserService service = getService();
        when(repository.existsById(1)).thenReturn(true);
        service.deleteById(1);
        verify(repository).deleteById(1);
    }

    @PostConstruct
    public UserService getService(){
        if  (service == null){
            service = new UserService(repository, loginDataMapper, passwordEncoder );
        }
        return service;
    }
}
