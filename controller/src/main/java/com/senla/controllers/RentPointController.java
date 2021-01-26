package com.senla.controllers;

import com.senla.dto.PageDTO;
import com.senla.dto.RentPointDTO;
import com.senla.services.RentPointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentPointController {

    private Logger logger = LoggerFactory.getLogger(RentPointController.class);

    private RentPointService service;

    @Autowired
    public RentPointController(RentPointService service) {
        this.service = service;
    }

    @GetMapping("/user/rentPoint/{id}")
    public ResponseEntity<RentPointDTO> getRentPointById(@PathVariable int id) {
        logger.info("find a RentPoint by id {}", id);
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/user/rentPoint")
    public ResponseEntity<PageDTO> getAllRentPoints(@RequestParam (value = "page") int page) {
        logger.info("find all RentPoint");
        return new ResponseEntity<>(service.getAll(page), HttpStatus.OK);
    }

    @PostMapping("/admin/rentPoint")
    public ResponseEntity<RentPointDTO> saveRentPoint(@RequestBody RentPointDTO rentPointDTO) {
        logger.info("save RentPoint -- {}", rentPointDTO);
        return new ResponseEntity<>(service.save(rentPointDTO), HttpStatus.CREATED);
    }

    @PutMapping("/admin/rentPoint")
    public ResponseEntity<RentPointDTO> updateRentPoint(@RequestBody RentPointDTO rentPointDTO) {
        logger.info("update RentPoint -- {}", rentPointDTO);
        return new ResponseEntity<>(service.update(rentPointDTO), HttpStatus.OK);
    }

    @DeleteMapping("/admin/rentPoint/{id}")
    public ResponseEntity<String> deleteRentPointById(@PathVariable int id){
        logger.info("delete RentPoint with id {}", id);
        service.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
