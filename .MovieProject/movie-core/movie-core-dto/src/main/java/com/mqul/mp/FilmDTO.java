package com.mqul.mp;

import java.util.List;

public class FilmDTO
{
    private Integer id;
    private String filmName;
    private String filmId;
    private String imdbRating;
    private String filmYear;
    private List<DirectorDTO> directors;
    private List<ActorDTO> actors;

    public FilmDTO()
    {
        //
    }

    public FilmDTO(Integer id, String filmName, String filmId, String imdbRating, String filmYear, List<DirectorDTO> directors, List<ActorDTO> actors)
    {
        this.id = id;
        this.filmName = filmName;
        this.filmId = filmId;
        this.imdbRating = imdbRating;
        this.filmYear = filmYear;
        this.directors = directors;
        this.actors = actors;
    }

    public Integer getId()
    {
        return id;
    }

    public String getFilmName()
    {
        return filmName;
    }

    public String getFilmId()
    {
        return filmId;
    }

    public String getImdbRating()
    {
        return imdbRating;
    }

    public String getFilmYear()
    {
        return filmYear;
    }

    public List<DirectorDTO> getDirectors()
    {
        return directors;
    }

    public List<ActorDTO> getActors()
    {
        return actors;
    }
}
