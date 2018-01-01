package com.mqul.mp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mqul
 * 
 * actor model mapping to actor tbl
 */
@Table(name = "Lookup_Film_Person")
@Entity
public class LookupFilmPerson {

    @Id
    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "actor_id")
    private Integer actorId;

    @Column(name = "director_id")
    private Integer directorId;

    public LookupFilmPerson()
    {
        //
    }

    public LookupFilmPerson(int filmId, Integer actorID, Integer directorId)
    {
        this.filmId = filmId;
        this.actorId = actorID;
        this.directorId = directorId;
    }

    public Integer getFilmId()
    {
        return filmId;
    }

    public void setFilmId(Integer filmId)
    {
        this.filmId = filmId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId)
    {
        this.actorId = actorId;
    }

    public Integer getDirectorId()
    {
        return directorId;
    }

    public void setDirectorId(Integer directorId)
    {
        this.directorId = directorId;
    }
}