package com.taufik.challenge4.service;

import com.taufik.challenge4.dao.FilmRepository;
import com.taufik.challenge4.dao.ScheduleRepository;
import com.taufik.challenge4.model.Film;
import com.taufik.challenge4.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService{
    FilmRepository filmRepository;
    ScheduleRepository scheduleRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, ScheduleRepository scheduleRepository) {
        this.filmRepository = filmRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> findScheduleByFilmcode(int filmcode) {
        return filmRepository.findById(filmcode).get().getScheduleList();
    }

    @Override
    public List<Film> findByStatusTayang(String statustayang) {
        return filmRepository.findByStatustayang(statustayang);
    }

    @Override
    public Optional<Film> findById(int filmcode) {
        return filmRepository.findById(filmcode);
    }

    @Override
    public Film save(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public void deleteById(int filmcode) {
        filmRepository.deleteById(filmcode);
    }
}
