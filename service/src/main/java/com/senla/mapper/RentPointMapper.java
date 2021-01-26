package com.senla.mapper;

import com.senla.dto.RentPointDTO;
import com.senla.dto.RentStoryDTO;
import com.senla.entity.RentPoint;
import com.senla.entity.RentStory;
import com.senla.repos.CityRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RentPointMapper implements MapperAPI<RentPoint, RentPointDTO> {

    @Autowired
    private MainMapper modelMapper;

    @Autowired
    private CityRepository cityRepository;

    private TypeMap<RentPoint, RentPointDTO> toDTOTypeMap;
    private TypeMap<RentPointDTO, RentPoint> toEntity;

    public RentPointDTO toDto(RentPoint rentPoint) {
        if(toDTOTypeMap == null){
            toDTOTypeMap = modelMapper.createTypeMap(RentPoint.class, RentPointDTO.class);}
        toDTOTypeMap.addMappings(mapping -> mapping.map(singleRentPoint -> rentPoint.getCity().getCityId(), RentPointDTO::setCityId));
        return Objects.isNull(rentPoint) ? null : modelMapper.map(rentPoint, RentPointDTO.class);
    }

    public RentPoint toEntity(RentPointDTO rentPointDTO){
        if (toEntity == null) {
            toEntity = modelMapper.createTypeMap(rentPointDTO, RentPoint.class);
        }
        toEntity
                .addMappings(m -> m.skip(RentPoint::setCity))
                .setPostConverter(mappingContext -> {
                    RentPointDTO dto = mappingContext.getSource();
                    RentPoint entity = mappingContext.getDestination();
                    if (dto.getCityId() != null) {
                        entity.setCity(cityRepository.getOne(dto.getCityId()));
                    }
                    if (dto.getCityId() != null) {
                        entity.setCity(cityRepository.getOne(dto.getCityId()));
                    }
                    return entity;
                });

        return Objects.isNull(rentPointDTO) ? null : modelMapper.map(rentPointDTO, RentPoint.class);

    }

}
