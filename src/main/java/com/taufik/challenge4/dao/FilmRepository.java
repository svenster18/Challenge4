package com.taufik.challenge4.dao;

import java.util.List;
import java.util.Optional;

import com.taufik.challenge4.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film,Integer> {
    // Query method
    List<Film> findByStatustayang(String statustayang);
}
