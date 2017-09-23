package com.mqul.mp;

public class DirectorDTO
{
    private Integer id;
    private String directorId;
    private String firstNames;
    private String lastName;

    public DirectorDTO()
    {
        //
    }

    public DirectorDTO(Integer id, String directorId, String firstNames, String lastName)
    {
        this.id = id;
        this.directorId = directorId;
        this.firstNames = firstNames;
        this.lastName = lastName;
    }

    public Integer getId()
    {
        return id;
    }

    public String getDirectorId()
    {
        return directorId;
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
