package com.mqul.mp;

public class DirectorDTO
{
    private int id;
    private String firstNames;
    private String lastName;
    private String imdbRef;

    public DirectorDTO()
    {
        //
    }

    public DirectorDTO(int id, String firstNames, String lastName, String imdbRef)
    {
        this.id = id;
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.imdbRef = imdbRef;
    }

    public int getId()
    {
        return id;
    }

    public String getFirstNames()
    {
        return firstNames;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getImdbRef()
    {
        return imdbRef;
    }
}
