package com.mqul.mp.service;

import com.mqul.mp.Actor;
import com.mqul.mp.ActorDTO;
import com.mqul.mp.DirectorDTO;
import com.mqul.mp.FilmDTO;

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
     * Add an existing actor to an existing film, will fail either are not present/found
     *
     * @param filmId
     * @param actorId
     * @return
     */
    FilmDTO addActorToFilm(int filmId, int actorId);

    /**
     * Add an existing director to an existing film, will fail either are not present/found
     *
     * @param filmId
     * @param directorId
     * @return
     */
    FilmDTO addDirectorToFilm(int filmId, int directorId);

    /**
     * Add existing actors to an existing film in bulk, will fail either are not present/found
     *
     * @param filmId
     * @param actorIds
     * @return
     */
    FilmDTO addActorsToFilm(int filmId, List<Integer> actorIds);

    /**
     * Add an existing director to an existing film, will fail either are not present/found
     *
     * @param filmId
     * @param directorIds
     * @return
     */
    FilmDTO addDirectorsToFilm(int filmId, List<Integer> directorIds);

    /**
     * Remove association between film and actor
     *
     * @param filmId
     * @param actorId
     * @return
     */
    FilmDTO removeActorFromFilm(int filmId, int actorId);

    /**
     * Remove association between film and director
     *
     * @param filmId
     * @param directorId
     * @return
     */
    FilmDTO removeDirectorFromFilm(int filmId, int directorId);

    /**
     * Remove a film by its ID
     *
     * @param filmId
     */
    void deleteFilm(int filmId);
}
