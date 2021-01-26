package com.senla.services;


import com.senla.dto.DiscountDTO;
import com.senla.entity.Discount;
import com.senla.mapper.DiscountMapper;
import com.senla.repos.DiscountRepository;
import com.senla.serviceAPI.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class DiscountService extends AbstractService<Discount, DiscountDTO, DiscountRepository, DiscountMapper> {

    public DiscountService(DiscountRepository repository, DiscountMapper mapper) {
        super(repository, mapper);
    }

}