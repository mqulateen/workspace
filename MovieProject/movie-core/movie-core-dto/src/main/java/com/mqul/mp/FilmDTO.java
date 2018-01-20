package com.mqul.mp;

import java.util.List;
import java.util.Objects;

public class FilmDTO
{
    private int id;
    private String filmName;
    private String imdbRef;
    private double imdbRating;
    private int filmYear;
    private List<DirectorDTO> directors;
    private List<ActorDTO> actors;

    public FilmDTO()
    {
        //
    }

    public FilmDTO(int id, String filmName, String imdbRef, double imdbRating, int filmYear, List<DirectorDTO> directors, List<ActorDTO> actors)
    {
        this.id = id;
        this.filmName = filmName;
        this.imdbRef = imdbRef;
        this.imdbRating = imdbRating;
        this.filmYear = filmYear;
        this.directors = directors;
        this.actors = actors;
    }

    public int getId()
    {
        return id;
    }

    public String getFilmName()
    {
        return filmName;
    }

    public String getImdbRef()
    {
        return imdbRef;
    }

    public double getImdbRating()
    {
        return imdbRating;
    }

    public int getFilmYear()
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

    /**   ensure the name/id values exist   **/
    public boolean isValid(){
        return !(Objects.isNull(id))
               &&!(Objects.isNull(filmName) || filmName.isEmpty());
    }
}
