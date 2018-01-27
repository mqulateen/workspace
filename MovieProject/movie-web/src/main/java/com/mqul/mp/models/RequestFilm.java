package com.mqul.mp.models;

import javax.validation.constraints.Size;

public class RequestFilm
{
    private String filmName;
    private double imdbRating;

    @Size(max = 7)
    private String imdbRef;

    @Size(max = 4)
    private int filmYear;

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getImdbRef() {
        return imdbRef;
    }

    public void setImdbRef(String imdbRef) {
        this.imdbRef = imdbRef;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getFilmYear() {
        return filmYear;
    }

    public void setFilmYear(int filmYear) {
        this.filmYear = filmYear;
    }
}
