package com.taufik.challenge4.service;

import com.taufik.challenge4.model.Film;
import com.taufik.challenge4.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<Schedule> findScheduleByFilmcode(int filmcode);
    List<Film> findByStatusTayang(String statustayang);
    Optional<Film> findById(int filmcode);
    Film save(Film film);
    void deleteById(int filmcode);
}
