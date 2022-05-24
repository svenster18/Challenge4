package com.taufik.challenge4.controller;

import java.util.List;

import com.taufik.challenge4.AuthTokenFilter;
import com.taufik.challenge4.model.Film;
import com.taufik.challenge4.model.Schedule;
import com.taufik.challenge4.service.FilmServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FilmController {
    FilmServiceImpl filmServiceImpl;
    @Autowired
    public FilmController(FilmServiceImpl filmServiceImpl) {
        this.filmServiceImpl = filmServiceImpl;
    }

    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);
    @GetMapping(value="/listfilm")
    public List<Film> getFilm() {
        return filmServiceImpl.findByStatusTayang("sedang tayang");
    }
    @PostMapping(value="/schedule/{filmcode}")
    public List<Schedule> getScheduleByFilmcode(@PathVariable(value = "filmcode") int filmcode) {
        return filmServiceImpl.findScheduleByFilmcode(filmcode);
    }
    @PostMapping(value="/film")
    public Film addFilm(@RequestBody Film film) {
        logger.info("Film successfully added to database");
        return filmServiceImpl.save(film);
    }
    @PutMapping(value="/film/{filmcode}")
    public Film updateFilm(@PathVariable("filmcode") int filmcode, @RequestBody Film newFilm) {
        Film film = filmServiceImpl.findById(filmcode)
                .orElseThrow(()->new FilmNotFoundException("Film with "+filmcode+" is Not Found!"));
        film.setFilmname(newFilm.getFilmname());
        logger.info("Film successfully updated");
        return filmServiceImpl.save(film);
    }
    @DeleteMapping(value="/film/{filmcode}")
    public String deleteFilm(@PathVariable("filmcode") int filmcode) {
        Film film = filmServiceImpl.findById(filmcode)
                .orElseThrow(()->new FilmNotFoundException("Film with "+filmcode+" is Not Found!"));
        filmServiceImpl.deleteById(film.getFilmcode());
        logger.info("Film successfully deleted");
        return "Film with film code :"+filmcode+" is deleted";
    }
}
