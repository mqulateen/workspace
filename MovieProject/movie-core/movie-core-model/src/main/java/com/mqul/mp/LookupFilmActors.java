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
@Table(name = "Lookup_Film_Actors")
@Entity
public class LookupFilmActors {

    @Id
    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "id")
    private Integer actorId;

    public LookupFilmActors()
    {
        //
    }

    public LookupFilmActors(Integer filmId, Integer actorID)
    {
        this.filmId = filmId;
        this.actorId = actorID;
    }

    public Integer getFilmId(){return filmId;}

    public void setFilmId(Integer id){this.filmId = id;}

    public Integer getActorId(){return actorId;}

    public void setActorId(Integer id){this.actorId = id;}
}