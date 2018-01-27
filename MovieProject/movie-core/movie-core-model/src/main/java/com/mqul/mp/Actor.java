package com.mqul.mp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

    public Actor(String firstNames, String lastName, String imdbRef)
    {
        super(firstNames, lastName, imdbRef);
    }

    public int getID()
    {
        return id;
    }

    public ActorDTO transferToDTO()
    {
        return new ActorDTO(id, super.getFirstNames(), super.getLastName(), super.getImdbRef());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(getImdbRef(), actor.getImdbRef());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}