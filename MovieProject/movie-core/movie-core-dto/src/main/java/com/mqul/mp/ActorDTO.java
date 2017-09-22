package com.mqul.mp;

public class ActorDTO
{
    private Integer id;
    private String actorID;
    private String firstNames;
    private String lastName;

    public ActorDTO()
    {
        //
    }

    public ActorDTO(Integer id, String actorID, String firstNames, String lastName)
    {
        this.id = id;
        this.actorID = actorID;
        this.firstNames = firstNames;
        this.lastName = lastName;
    }

    public Integer getId()
    {
        return id;
    }

    public String getActorID()
    {
        return actorID;
    }

    public String getFirstNames()
    {
        return firstNames;
    }

    public String getLastName()
    {
        return lastName;
    }
}
