package com.senla.services;

import com.senla.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BDUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(BDUserDetailsService.class);

    private final UserService service;

    public BDUserDetailsService(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("find User by login {}", s);
        com.senla.entity.User user = service.findByLogin(s);
        if (user == null) {
            logger.warn("user {} not found", s);
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()
        ) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        authorities.containsAll(user.getRoles());
        logger.info("find a roles {}", user.getRoles());
        return new User(user.getLogin(), user.getPassword(), authorities);
    }
}
