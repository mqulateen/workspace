package com.mqul.mp;

public class ActorDTO
{
    private Integer id;
    private String actorId;
    private String firstNames;
    private String lastName;

    public ActorDTO()
    {
        //
    }

    public ActorDTO(Integer id, String actorId, String firstNames, String lastName)
    {
        this.id = id;
        this.actorId = actorId;
        this.firstNames = firstNames;
        this.lastName = lastName;
    }

    public Integer getId()
    {
        return id;
    }

    public String getActorId()
    {
        return actorId;
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
