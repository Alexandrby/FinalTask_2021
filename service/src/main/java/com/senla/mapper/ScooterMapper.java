package com.senla.mapper;

import com.senla.dto.ScooterDTO;
import com.senla.dto.SeasonTicketDTO;
import com.senla.entity.Scooter;
import com.senla.entity.SeasonTicket;
import com.senla.repos.ProfileRepository;
import com.senla.repos.RentPointRepository;
import com.senla.repos.ScooterRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class ScooterMapper implements MapperAPI<Scooter, ScooterDTO> {

    @Autowired
    private MainMapper modelMapper;

    @Autowired
    RentPointRepository rentPointRepository;

    private TypeMap<Scooter, ScooterDTO> toDTOTypeMap;
    private TypeMap<ScooterDTO, Scooter> toEntity;

    public ScooterDTO toDto(Scooter scooter) {
        if(toDTOTypeMap == null){
            toDTOTypeMap = modelMapper.createTypeMap(Scooter.class, ScooterDTO.class);}
        toDTOTypeMap.addMappings(mapping -> mapping.map(mappedRentPointId -> scooter.getRentPoint().getRentPointId(), ScooterDTO::setRentPointId));
        return Objects.isNull(scooter) ? null : modelMapper.map(scooter, ScooterDTO.class);
    }

    public Scooter toEntity(ScooterDTO scooterDTO){
        if (toEntity == null) {
            toEntity = modelMapper.createTypeMap(scooterDTO, Scooter.class);
        }
        toEntity
                .addMappings(m -> m.skip(Scooter::setRentPoint))
                .setPostConverter(mappingContext -> {
                    ScooterDTO dto = mappingContext.getSource();
                    Scooter entity = mappingContext.getDestination();

                    if (dto.getRentPointId() != null) {
                        entity.setRentPoint(rentPointRepository.getOne(dto.getRentPointId()));
                    }
                    return entity;
                });
        return Objects.isNull(scooterDTO) ? null : modelMapper.map(scooterDTO, Scooter.class);
    }

}
