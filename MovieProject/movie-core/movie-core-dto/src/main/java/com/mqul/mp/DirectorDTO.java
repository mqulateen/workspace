package com.mqul.mp;

public class DirectorDTO
{
    private Integer id;
    private String actorID;
    private String firstNames;
    private String lastName;

    public DirectorDTO()
    {
        //
    }

    public DirectorDTO(Integer id, String actorID, String firstNames, String lastName)
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
