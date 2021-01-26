package com.senla.services;

import com.senla.dto.UserDTO;
import com.senla.entity.User;
import com.senla.mapper.UserMapper;
import com.senla.repos.UserRepository;
import com.senla.serviceAPI.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserDTO, UserRepository, UserMapper> {

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, UserMapper mapper,@Autowired PasswordEncoder passwordEncoder  ) {
        super(repository, mapper);
        this.passwordEncoder = passwordEncoder;
    }

    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
