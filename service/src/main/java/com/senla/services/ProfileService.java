package com.senla.services;

import com.senla.dto.ProfileDTO;
import com.senla.entity.Profile;
import com.senla.mapper.ProfileMapper;
import com.senla.repos.ProfileRepository;
import com.senla.serviceAPI.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class ProfileService extends AbstractService<Profile, ProfileDTO, ProfileRepository, ProfileMapper> {

    public ProfileService(ProfileRepository repository, ProfileMapper mapper) {
        super(repository, mapper);
    }


}