package com.senla.services;

import com.senla.dto.SeasonTicketDTO;
import com.senla.entity.SeasonTicket;
import com.senla.mapper.SeasonTicketMapper;
import com.senla.repos.SeasonTicketRepository;
import com.senla.serviceAPI.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class SeasonTicketService extends AbstractService<SeasonTicket, SeasonTicketDTO, SeasonTicketRepository, SeasonTicketMapper> {

    public SeasonTicketService(SeasonTicketRepository repository, SeasonTicketMapper mapper) {
        super(repository, mapper);
    }

}