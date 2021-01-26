package com.senla.controllers;

import com.senla.dto.PageDTO;
import com.senla.dto.UserDTO;
import com.senla.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user/loginData/{id}")
    public ResponseEntity<UserDTO> getLoginDataById(@PathVariable int id) {
        logger.info("find a LoginData by id {}", id);
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/admin/loginData")
    public ResponseEntity<PageDTO> getAllLoginDates(@RequestParam (value = "page") int page) {
        logger.info("find all LoginDates");
        return new ResponseEntity<>(service.getAll(page), HttpStatus.OK);
    }

    @PostMapping("/admin/loginData")
    public ResponseEntity<UserDTO> saveLoginData(@RequestBody UserDTO userDTO) {
        logger.info("save LoginData -- {}", userDTO);
        return new ResponseEntity<>(service.save(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/admin/loginData")
    public ResponseEntity<UserDTO> updateLoginData(@RequestBody UserDTO userDTO) {
        logger.info("update LoginData -- {}", userDTO);
        return new ResponseEntity<>(service.update(userDTO), HttpStatus.OK);
    }


    @DeleteMapping("/admin/loginData/{id}")
    public ResponseEntity<String> deleteLoginDataById(@PathVariable int id) {
        logger.info("delete LoginData with id {}", id);
        service.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
