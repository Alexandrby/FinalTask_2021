package com.senla.mapper;

import com.senla.dto.RentStoryDTO;
import com.senla.dto.SeasonTicketDTO;
import com.senla.entity.Profile;
import com.senla.entity.RentStory;
import com.senla.entity.SeasonTicket;
import com.senla.repos.ProfileRepository;
import com.senla.repos.ScooterRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RentStoryMapper  implements MapperAPI<RentStory, RentStoryDTO>{

    @Autowired
    private MainMapper modelMapper;

    @Autowired
    ScooterRepository scooterRepository;

    @Autowired
    ProfileRepository profileRepository;

    private TypeMap<RentStory, RentStoryDTO> toDTOTypeMap;
    private TypeMap<RentStoryDTO, RentStory> toEntity;

    public RentStoryDTO toDto(RentStory rentStory) {
        if(toDTOTypeMap == null){
            toDTOTypeMap = modelMapper.createTypeMap(RentStory.class, RentStoryDTO.class);}
        toDTOTypeMap.addMappings(mapping -> mapping.map(mappedProfileId -> rentStory.getProfile().getProfileId(), RentStoryDTO::setProfileId));
        toDTOTypeMap.addMappings(mapping -> mapping.map(mappedScooterId -> rentStory.getScooter().getScooterId(), RentStoryDTO::setScooterId));
        return Objects.isNull(rentStory) ? null : modelMapper.map(rentStory, RentStoryDTO.class);
    }

    public RentStory toEntity(RentStoryDTO rentStoryDTO){

        if (toEntity == null) {
            toEntity = modelMapper.createTypeMap(rentStoryDTO, RentStory.class);
        }
        toEntity
                .addMappings(m -> m.skip(RentStory::setProfile))
                .addMappings(m -> m.skip(RentStory::setScooter))
                .setPostConverter(mappingContext -> {
                    RentStoryDTO dto = mappingContext.getSource();
                    RentStory entity = mappingContext.getDestination();
                    if (dto.getProfileId() != null) {
                        entity.setProfile(profileRepository.getOne(dto.getProfileId()));
                    }
                    if (dto.getScooterId() != null) {
                        entity.setScooter(scooterRepository.getOne(dto.getScooterId()));
                    }
                    return entity;
                });
        return Objects.isNull(rentStoryDTO) ? null : modelMapper.map(rentStoryDTO, RentStory.class);
    }
}
