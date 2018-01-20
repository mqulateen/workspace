package com.mqul.mp.service;

import com.mqul.mp.DirectorDTO;

import java.util.List;

public interface DirectorService 
{
    /**
     * Create a new director
     *
     * @param firstName
     * @param lastName
     * @param imdbRef
     * @return
     */
    DirectorDTO createDirector(String firstName, String lastName, String imdbRef);


    /**
     * Retrieve the director associated with the given ID
     *
     * @param id
     * @return
     */
    DirectorDTO getDirectorById(int id);


    /**
     * Retrieve the director associated with the given IMDB reference (ID)
     *
     * @param imdbRef
     * @return
     */
    DirectorDTO getDirectorByImdbRef(String imdbRef);


    /**
     * Retrieve all directors 
     *
     * @return
     */
    List<DirectorDTO> getDirectors();


    /**
     * Update an director by their id, updatable fields are optional
     *
     * @param id        - required
     * @param firstName - optional
     * @param lastName  - optional
     * @param imdbRef   - optional
     * @return
     */
    DirectorDTO updateDirector(int id, String firstName, String lastName, String imdbRef);


    /**
     * Remove an director from the DB
     *
     * @param id
     */
    void deleteDirector(int id);
}
