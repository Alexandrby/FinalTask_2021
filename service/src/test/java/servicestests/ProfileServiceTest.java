package servicestests;

import com.senla.dto.ProfileDTO;
import com.senla.dto.UserDTO;
import com.senla.entity.*;
import com.senla.mapper.ProfileMapper;
import com.senla.repos.ProfileRepository;
import com.senla.services.ProfileService;
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
public class ProfileServiceTest implements CRUDTest {

    ProfileMapper profileMapper = mock(ProfileMapper.class);
    ProfileRepository profileRepository = mock(ProfileRepository.class);

    Profile profile = new Profile();
    ProfileDTO profileDTO = new ProfileDTO();

    ProfileService service;

    @Test
    public void findByIdTest() {
        ProfileService service = getService();
        when(profileMapper.toDto(any())).thenReturn(profileDTO);
        when(profileRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(profile));
        ProfileDTO profileDTO = service.getOne(1);
        assertEquals(profileDTO, this.profileDTO);
        verify(profileRepository).findById(1);
    }

    @Test
    @Override
    public void findAllTest() {
        ProfileService service = getService();
        List<Profile> users = new ArrayList<>();
        users.add(profile);
        Page page = new PageImpl(users);
        when(profileMapper.toDto(any())).thenReturn(profileDTO);
        when(profileRepository.findAll(PageRequest.of(1, 10))).thenReturn(page);
        service.getAll(1);
        verify(profileRepository).findAll(PageRequest.of(1, 10));
    }



   @Test
    @Override
    public void saveTest() {
        ProfileService service = getService();
        profileDTO.setDiscountId(1);
        profileDTO.setUserId(1);
        when(profileMapper.toEntity(any())).thenReturn(profile);
        service.save(profileDTO);
        verify(profileRepository).save(any());
    }

    @Test
    @Override
    public void updateTest() {
        ProfileService service = getService();
        profileDTO.setDiscountId(1);
        profileDTO.setProfileId(1);
        when(profileRepository.existsById(0)).thenReturn(true);
        when(profileMapper.toEntity(any())).thenReturn(profile);
        service.update(profileDTO);
        verify(profileRepository).save(any());
    }

    @Test
    @Override
    public void deleteTest() {
        ProfileService service = getService();
        when(profileRepository.existsById(1)).thenReturn(true);
        service.deleteById(1);
        verify(profileRepository).deleteById(1);
    }

    @PostConstruct
    public ProfileService getService() {
        if (service == null) {
            service = new ProfileService(profileRepository, profileMapper);
        }
        return service;
        }
    }

