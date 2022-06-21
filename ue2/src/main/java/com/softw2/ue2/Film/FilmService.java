package com.softw2.ue2.Film;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService
{
    private static List<Film> films = new ArrayList<Film>();

    public List<Film> getFilms()
    {
        return films;
    }

    public List<Film> getFilmByID(int id)
    {
        return getFilms().stream().filter(film -> film.getId() == id).collect(Collectors.toList());
    }

    public List<Film> getFilmByName(String name)
    {
        return getFilms().stream().filter(film -> film.getName().equals(name)).collect(Collectors.toList());
    }

    public void addFilm(Film film)
    {
        films.add(film);
    }
}
