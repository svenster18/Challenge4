package com.taufik.challenge4.dao;

import com.taufik.challenge4.model.Film;
import com.taufik.challenge4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}
