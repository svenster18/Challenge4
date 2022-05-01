package com.taufik.challenge4.service;

import com.taufik.challenge4.model.Film;
import com.taufik.challenge4.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    User save(User user);
    void deleteByUsername(String username);
}
