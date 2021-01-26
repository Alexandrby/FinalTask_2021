package com.senla.controllers;

import com.senla.dto.PageDTO;
import com.senla.dto.ProfileDTO;
import com.senla.dto.UserDTO;
import com.senla.services.ProfileService;
import com.senla.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    private Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private ProfileService service;

    @Autowired
    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("/user/users/{id}")
    public ResponseEntity<ProfileDTO> getUserById(@PathVariable int id) {
        logger.info("find a user by id {}", id);
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/user/users")
    public ResponseEntity<ProfileController> getAllUsers(@RequestParam(value = "page") int page) {
        logger.info("find all users");
        return new ResponseEntity(service.getAll(page), HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<ProfileDTO> saveUser(@RequestBody ProfileDTO profileDTO) {
        logger.info("save user -- {}", profileDTO);
        return new ResponseEntity<>(service.save(profileDTO), HttpStatus.CREATED);
    }

    @PutMapping("/admin/users")
    public ResponseEntity<ProfileDTO> updateUser(@RequestBody ProfileDTO profileDTO) {
        logger.info("update user -- {}", profileDTO);
        return new ResponseEntity<>(service.update(profileDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        logger.info("delete user with id {}", id);
        service.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


}
