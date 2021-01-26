package com.senla.mapper;

import com.senla.dto.ProfileDTO;
import com.senla.dto.SeasonTicketDTO;
import com.senla.entity.Profile;
import com.senla.entity.SeasonTicket;
import com.senla.repos.DiscountRepository;
import com.senla.repos.ProfileRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SeasonTicketMapper implements MapperAPI<SeasonTicket, SeasonTicketDTO>{

    @Autowired
    private MainMapper modelMapper;

    @Autowired
    ProfileRepository profileRepository;

    private TypeMap<SeasonTicket, SeasonTicketDTO> toDTOTypeMap;
    private TypeMap<SeasonTicketDTO, SeasonTicket> toEntity;

    public SeasonTicketDTO toDto(SeasonTicket seasonTicket) {
        if(toDTOTypeMap == null){
            toDTOTypeMap = modelMapper.createTypeMap(SeasonTicket.class, SeasonTicketDTO.class);}
        toDTOTypeMap.addMappings(mapping -> mapping.map(mappedSeasonTicketId -> seasonTicket.getProfile().getProfileId(), SeasonTicketDTO::setProfileId));
        return Objects.isNull(seasonTicket) ? null : modelMapper.map(seasonTicket, SeasonTicketDTO.class);
    }

    public SeasonTicket toEntity(SeasonTicketDTO seasonTicketDTO){
        if (toEntity == null) {
            toEntity = modelMapper.createTypeMap(seasonTicketDTO, SeasonTicket.class);
        }
        toEntity
                .addMappings(m -> m.skip(SeasonTicket::setProfile))
                .setPostConverter(mappingContext -> {
                    SeasonTicketDTO dto = mappingContext.getSource();
                    SeasonTicket entity = mappingContext.getDestination();

                    if (dto.getProfileId() != null) {
                        entity.setProfile(profileRepository.getOne(dto.getProfileId()));
                    }
                    return entity;
                });

        return Objects.isNull(seasonTicketDTO) ? null : modelMapper.map(seasonTicketDTO, SeasonTicket.class);
    }
}
