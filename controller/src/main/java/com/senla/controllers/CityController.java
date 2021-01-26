package com.senla.controllers;

import com.senla.dto.CityDTO;
import com.senla.dto.PageDTO;
import com.senla.services.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {

    private Logger logger = LoggerFactory.getLogger(CityController.class);

    private CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/user/city/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable int id) {
        logger.info("find a city by id {}", id);
        return new ResponseEntity<CityDTO>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/user/city")
    public ResponseEntity<PageDTO> getAllCities(@RequestParam (value = "page") int page) {
        logger.info("find all cities");
        return new ResponseEntity<>(service.getAll(page), HttpStatus.OK);
    }

    @PostMapping("/admin/city")
    public ResponseEntity<CityDTO> saveCity(@RequestBody CityDTO cityDTO) {
        logger.info("save city -- {}", cityDTO);
        return new ResponseEntity<>(service.save(cityDTO), HttpStatus.CREATED);
    }

    @PutMapping("/admin/city")
    public ResponseEntity<CityDTO> updateCity(@RequestBody CityDTO cityDTO) {
        logger.info("update city -- {}", cityDTO);
        return new ResponseEntity<>(service.update(cityDTO), HttpStatus.CREATED);
    }


    @DeleteMapping("/admin/city/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        logger.info("delete city with id {}", id);
        service.deleteById(id);
        return new ResponseEntity<>( "deleted", HttpStatus.OK);
    }
}
