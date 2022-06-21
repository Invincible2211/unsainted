package com.softw2.ue1.Film;

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

    public void addFilm(Film film)
    {
        films.add(film);
    }
}
