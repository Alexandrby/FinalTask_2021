package com.senla.controllers;

import com.senla.dto.PageDTO;
import com.senla.dto.ScooterDTO;
import com.senla.services.ScooterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScooterController {

    private Logger logger = LoggerFactory.getLogger(ScooterController.class);

    private ScooterService service;

    @Autowired
    public ScooterController(ScooterService service) {
        this.service = service;
    }

    @GetMapping("/user/scooter/{id}")
    public ResponseEntity<ScooterDTO> getScooterById(@PathVariable int id) {
        logger.info("find a scooter by id {}", id);
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/user/scooter/page/{page}")
    public ResponseEntity<PageDTO> getAllScooters(@PathVariable int page) {
        logger.info("find all scooters");
        PageDTO pageDTO = service.getAll( page);
        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @PostMapping("/admin/scooter")
    public ResponseEntity<ScooterDTO> saveScooter(@RequestBody ScooterDTO scooterDTO) {
        logger.info("save scooter -- {}", scooterDTO);
        return new ResponseEntity<>(service.save(scooterDTO), HttpStatus.CREATED);
    }

    @PutMapping("/admin/scooter")
    public ResponseEntity<ScooterDTO> updateScooter(@RequestBody ScooterDTO scooterDTO) {
        logger.info("update scooter -- {}", scooterDTO);
        return new ResponseEntity<>(service.update(scooterDTO), HttpStatus.CREATED);
    }


    @DeleteMapping("/admin/scooter/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        logger.info("delete scooter with id {}", id);
        service.deleteById(id);
        return new ResponseEntity<>("Deleted",  HttpStatus.OK);
    }

}
