package com.mqul.mp;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author mqul
 * 
 * actor model mapping to actor tbl
 */
@Table(name = "actors")
@Entity
public class Actor extends Person implements Serializable, TransferableObject<ActorDTO>
{
    @Column(name = "imdb_id")
    private String actorID;

    public Actor()
    {
        //
    }

    public Actor(Integer id, String actorID, String firstNames, String lastName)
    {
        super(firstNames, lastName);
        this.actorID = actorID;
    }

    public String getActorID()
    {
        return actorID;
    }

    public void setActorID(String actorID)
    {
        this.actorID = actorID;
    }

    public ActorDTO transferToDTO()
    {
        return new ActorDTO(super.getId(), actorID, super.getFirstNames(), super.getLastName());
    }
}