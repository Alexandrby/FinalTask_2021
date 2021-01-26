package com.senla.controllers;

import com.senla.dto.DiscountDTO;
import com.senla.dto.PageDTO;
import com.senla.services.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiscountController {

    private final Logger logger = LoggerFactory.getLogger(DiscountController.class);
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/user/discount/{id}")
    public ResponseEntity<DiscountDTO> getDiscountById(@PathVariable Integer id) {
        logger.info("find a discount by id {}", id);
        return new ResponseEntity<>(discountService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/user/discount")
    public ResponseEntity<PageDTO> getAllDiscount(@RequestParam(value = "page") int page) {
        logger.info("find all discounts");
        return new ResponseEntity<>(discountService.getAll(page), HttpStatus.OK);
    }

    @PostMapping("/admin/discount")
    public ResponseEntity<DiscountDTO> saveDiscount(@RequestBody DiscountDTO discountDTO) {
        logger.info("save discount -- {}", discountDTO);
        return new ResponseEntity<>(discountService.save(discountDTO), HttpStatus.CREATED);
    }

    @PutMapping("/admin/discount")
    public ResponseEntity<DiscountDTO> updateDiscount(@RequestBody DiscountDTO discountDTO) {
        logger.info("update discount -- {}", discountDTO);
        return new ResponseEntity<>(discountService.update(discountDTO), HttpStatus.CREATED);
    }


    @DeleteMapping("/admin/discount/{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        logger.info("delete discount with id {}", id);
        discountService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
