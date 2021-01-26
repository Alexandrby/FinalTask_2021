package com.senla.repos;

import com.senla.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<User> {
    User findByLogin(String login);
}
