package com.senla.services;


import com.senla.dto.PageDTO;
import com.senla.dto.RentStoryDTO;
import com.senla.entity.RentStory;
import com.senla.mapper.RentStoryMapper;
import com.senla.repos.RentStoryRepository;
import com.senla.serviceAPI.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class RentStoryService extends AbstractService<RentStory, RentStoryDTO, RentStoryRepository, RentStoryMapper> {

    public RentStoryService(RentStoryRepository repository, RentStoryMapper mapper) {
        super(repository, mapper);
    }

    /*public PageDTO findByUserId(int userId, int page) {
        Page<RentStory> ePage = repository.findAllByUserIdLike(userId, PageRequest.of(page, 10 ));

        PageDTO pageDTO = new PageDTO();
        pageDTO.setList(ePage.getContent().stream().map(mapper::toDto).collect(Collectors.toCollection(ArrayList::new)));
        pageDTO.setTotalPages(ePage.getTotalPages());
        return pageDTO;
    }*/
}
