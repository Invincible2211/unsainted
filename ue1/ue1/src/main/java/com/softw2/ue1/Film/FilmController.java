package com.softw2.ue1.Film;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class FilmController
{
    private FilmService service;

    @Autowired
    public FilmController(FilmService service)
    {
        this.service = service;
    }

    @GetMapping
    public List<Film> getFilms()
    {
        return service.getFilms();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String addFilm(@RequestBody Film film)
    {
        if (film.getName() == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        film.setId();
        service.addFilm(film);
        return null;
    }
}