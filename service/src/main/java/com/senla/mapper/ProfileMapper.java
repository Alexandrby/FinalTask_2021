package com.senla.mapper;

import com.senla.dto.ProfileDTO;
import com.senla.entity.Profile;
import com.senla.repos.DiscountRepository;
import com.senla.repos.UserRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class ProfileMapper implements MapperAPI<Profile, ProfileDTO>{

    @Autowired
    private MainMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiscountRepository discountRepository;

    private TypeMap<Profile, ProfileDTO> toDTOTypeMap;
    private TypeMap<ProfileDTO, Profile> toEntity;

    public ProfileDTO toDto(Profile profile) {
        if (toDTOTypeMap == null) {
            toDTOTypeMap = modelMapper.createTypeMap(Profile.class, ProfileDTO.class);
        }
        toDTOTypeMap.addMappings(mapping -> mapping.map(mapDiscountId -> profile.getDiscount().getDiscountId(), ProfileDTO::setDiscountId));
        toDTOTypeMap.addMappings(mapping -> mapping.map(mappedUserId -> profile.getUser().getUserId(), ProfileDTO::setUserId));
        return Objects.isNull(profile) ? null : modelMapper.map(profile, ProfileDTO.class);
    }

    public Profile toEntity(ProfileDTO profileDTO) {

        if (toEntity == null) {
            toEntity = modelMapper.createTypeMap(profileDTO, Profile.class);
        }
        toEntity
                .addMappings(m -> m.skip(Profile::setUser))
                .addMappings(m -> m.skip(Profile::setDiscount))
                .setPostConverter(mappingContext -> {
                    ProfileDTO dto = mappingContext.getSource();
                    Profile entity = mappingContext.getDestination();
                    if (dto.getUserId() != null) {
                        entity.setUser(userRepository.getOne(dto.getUserId()));
                    }
                    if (dto.getDiscountId() != null) {
                        entity.setDiscount(discountRepository.getOne(dto.getDiscountId()));
                    }
                    return entity;
                });

       return Objects.isNull(profileDTO) ? null : modelMapper.map(profileDTO, Profile.class);
    }
}