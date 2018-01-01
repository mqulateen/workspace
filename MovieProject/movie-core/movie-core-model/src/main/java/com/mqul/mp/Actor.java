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
    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public Actor()
    {
        //
    }

    public Actor(String imdbRef, String firstNames, String lastName)
    {
        super(imdbRef, firstNames, lastName);
    }

    public int getID()
    {
        return id;
    }

    public ActorDTO transferToDTO()
    {
        return new ActorDTO(id, super.getImdbRef(), super.getFirstNames(), super.getLastName());
    }
}