package com.senla.services;


import com.senla.dto.ScooterDTO;
import com.senla.entity.Scooter;
import com.senla.mapper.ScooterMapper;
import com.senla.repos.ScooterRepository;
import com.senla.serviceAPI.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class ScooterService extends AbstractService<Scooter, ScooterDTO, ScooterRepository, ScooterMapper> {

    public ScooterService(ScooterRepository repository, ScooterMapper mapper) {
        super(repository, mapper);
    }

}