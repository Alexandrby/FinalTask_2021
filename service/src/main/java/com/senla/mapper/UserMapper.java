package com.senla.mapper;

import com.senla.dto.ProfileDTO;
import com.senla.dto.UserDTO;
import com.senla.entity.Profile;
import com.senla.entity.Role;
import com.senla.entity.User;
import com.senla.repos.ProfileRepository;
import com.senla.repos.RoleRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class UserMapper implements MapperAPI<User, UserDTO> {

    @Autowired
    private MainMapper modelMapper;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
            private RoleRepository roleRepository;


    TypeMap<User, UserDTO> toDTOTypeMap;
    TypeMap<UserDTO, User> toEntity;

    public UserDTO toDto(User user ) {
        if (toDTOTypeMap == null) {
            toDTOTypeMap = modelMapper.createTypeMap(User.class, UserDTO.class);
        }
        toDTOTypeMap
                .addMappings(m -> m.skip(UserDTO::setRole))
                .setPostConverter(mappingContext -> {
                    UserDTO dto = mappingContext.getDestination();
                    User entity = mappingContext.getSource();
                    if(entity.getRoles() != null && entity.getRoles().size() > 0) {
                        dto.setRole(entity.getRoles().get(0).getName());
                    }
                    return dto;
                });
       return Objects.isNull(user) ? null : modelMapper.map(user, UserDTO.class);
    }

    public User toEntity(UserDTO userDTO) {

        if (toEntity == null) {
            toEntity = modelMapper.createTypeMap(userDTO, User.class);
        }

        toEntity
                .addMappings(m -> m.skip(User::setProfile))
                .addMappings(m -> m.skip(User::setRoles))
                .setPostConverter(mappingContext -> {
                    UserDTO dto = mappingContext.getSource();
                    User entity = mappingContext.getDestination();
                    if (dto.getProfileId() != null) {
                        entity.setProfile(profileRepository.getOne(dto.getProfileId()));
                        //entity.getProfile().setUser(entity);
                    }
                    if(StringUtils.hasText(dto.getRole())) {
                        entity.setRoles(roleRepository.findByName(dto.getRole()));
                    }
                    return entity;
                });

        User user = Objects.isNull(userDTO) ? null : modelMapper.map(userDTO, User.class);
        return user;
    }

}
