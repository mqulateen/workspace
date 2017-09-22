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
public class Actor implements Serializable, TransferableObject<ActorDTO>
{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Integer id;

    @Column(name = "imdb_id")
    private String actorID;

    @Column(name = "actor_firstNames")
    private String firstNames;

    @Column(name = "actor_lastName")
    private String lastName;

    public Actor()
    {
        //
    }

    public Actor(String actorID, String firstNames, String lastName)
    {
        this.actorID = actorID;
        this.firstNames = firstNames;
        this.lastName = lastName;
    }

    public Integer getId(){return id;}

    public void setId(Integer id){this.id = id;}

    public String getActorID() {
        return actorID;
    }

    public void setActorID(String actorID) {
        this.actorID = actorID;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ActorDTO transferToDTO()
    {
        return new ActorDTO(id, actorID, firstNames, lastName);
    }
}