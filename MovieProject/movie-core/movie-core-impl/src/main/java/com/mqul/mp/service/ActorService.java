package com.mqul.mp.service;

import com.mqul.mp.ActorDTO;

import java.util.List;

public interface ActorService
{
    /**
     * Create a new actor
     *
     * @param firstName
     * @param lastName
     * @param imdbRef
     * @return
     */
    ActorDTO createActor(String firstName, String lastName, String imdbRef);


    /**
     * Retrieve the actor associated with the given ID
     *
     * @param id
     * @return
     */
    ActorDTO getActorById(int id);


    /**
     * Retrieve the actor associated with the given IMDB reference (ID)
     *
     * @param imdbRef
     * @return
     */
    ActorDTO getActorByImdbRef(String imdbRef);


    /**
     * Retrieve all actors
     *
     * @return
     */
    List<ActorDTO> getActors();


    /**
     * Update an actor by their id, updatable fields are optional
     *
     * @param id        - required
     * @param firstName - optional
     * @param lastName  - optional
     * @param imdbRef   - optional
     * @return
     */
    ActorDTO updateActor(int id, String firstName, String lastName, String imdbRef);


    /**
     * Remove an actor from the DB
     *
     * @param id
     */
    void deleteActor(int id);
}
