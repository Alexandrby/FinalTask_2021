package com.senla.controllers;
import com.senla.dto.UserDTO;
import com.senla.entity.User;
import com.senla.security.JwtTokenProvider;
import com.senla.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return "OK";
    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody UserDTO request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtTokenProvider.createToken(user.getLogin());
        return new ResponseEntity(token, HttpStatus.OK);
    }
}
