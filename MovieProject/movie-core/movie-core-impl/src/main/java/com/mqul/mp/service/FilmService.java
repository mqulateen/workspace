package com.mqul.mp.service;

import com.mqul.mp.*;

import java.util.List;

public interface FilmService
{
    /**
     * Retrieve all films
     *
     * @return
     */
    List<FilmDTO> getAllFilms();

    /**
     * Retrieve a single film by its id
     *
     * @param id
     * @return
     */
    FilmDTO getFilmById(int id);

    /**
     * Retrieve a single film by its IMDB Reference (ID)
     *
     * @param imdbRef
     * @return
     */
    FilmDTO getFilmByImdbRef(String imdbRef);

    /**
     * Retrieve all actors associated with this film
     *
     * @param id
     * @return
     */
    List<ActorDTO> getActorsByFilm(int id);

    /**
     * Retrieve all directors associated with this film
     *
     * @param id
     * @return
     */
    List<DirectorDTO> getDirectorsByFilm(int id);

    /**
     * Create a new film
     *
     * @param filmName
     * @param filmYear
     * @param imdbRef
     * @param imdbRating
     * @return
     */
    FilmDTO createFilm(String filmName, int filmYear, String imdbRef, double imdbRating);

    /**
     * Update an existing Film
     *
     * @param id - required to find film
     * @param filmName   - optional
     * @param filmYear   - optional
     * @param imdbRef    - optional
     * @param imdbRating - optional
     * @return
     */
    FilmDTO updateFilm(int id, String filmName, Integer filmYear, String imdbRef, Double imdbRating);

    /**
     * Add an existing actor/director to an existing film, will fail if either are not present/found
     *
     * @param filmId
     * @param personId
     * @param type
     * @return
     */
    FilmDTO addPersonToFilm(int filmId, int personId, PersonType type);

    /**
     * Remove association between film and actor/director
     *
     * @param filmId
     * @param type
     * @return
     */
    FilmDTO removePersonFromFilm(int filmId, int personId, PersonType type);

    /**
     * Remove a film by its ID
     *
     * @param filmId
     */
    void deleteFilm(int filmId);
}
