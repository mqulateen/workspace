package com.mqul.mp;

public class DirectorDTO
{
    private int id;
    private String imdbRef;
    private String firstNames;
    private String lastName;

    public DirectorDTO()
    {
        //
    }

    public DirectorDTO(int id, String imdbRef, String firstNames, String lastName)
    {
        this.id = id;
        this.imdbRef = imdbRef;
        this.firstNames = firstNames;
        this.lastName = lastName;
    }

    public int getId()
    {
        return id;
    }

    public String getImdbRef()
    {
        return imdbRef;
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
