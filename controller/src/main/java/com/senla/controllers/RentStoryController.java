package com.senla.controllers;

import com.senla.dto.PageDTO;
import com.senla.dto.RentStoryDTO;
import com.senla.services.RentStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentStoryController {

    private Logger logger = LoggerFactory.getLogger(RentStoryController.class);

    private RentStoryService service;

    @Autowired
    public RentStoryController(RentStoryService service) {
        this.service = service;
    }

    @GetMapping("/user/rentStory/{id}")
    public ResponseEntity<RentStoryDTO> getRentStoryById(@PathVariable int id) {
        logger.info("find a RentStory by id {}", id);
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/user/rentStory")
    public ResponseEntity<PageDTO> getAllRentStories(@RequestParam(value = "page") int page) {
        logger.info("find all RentStories");
        PageDTO pageDTO = service.getAll(page);
        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

   /* @GetMapping("/user/rentStory/")
    public ResponseEntity<PageDTO> getAllRentStoriesByUser(@RequestParam(value = "page") int page,
                                                           @RequestParam(value = "user") int user) {
        logger.info("find all RentStories by user id");
        return new ResponseEntity<>(service.findByUserId(user, page), HttpStatus.OK);
    }*/

    @PostMapping("/admin/rentStory")
    public ResponseEntity<RentStoryDTO> saveRentStory(@RequestBody RentStoryDTO rentStoryDTO) {
        logger.info("save RentStory -- {}", rentStoryDTO);
        return new ResponseEntity<>(service.save(rentStoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/admin/rentStory")
    public ResponseEntity<RentStoryDTO> updateRentStory(@RequestBody RentStoryDTO rentStoryDTO) {
        logger.info("update RentStory -- {}", rentStoryDTO);
        return new ResponseEntity<>(service.update(rentStoryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/admin/rentStory/{id}")
    public ResponseEntity<String> deleteRentStoryById(@PathVariable int id) {
        logger.info("delete RentStory with id {}", id);
        service.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
