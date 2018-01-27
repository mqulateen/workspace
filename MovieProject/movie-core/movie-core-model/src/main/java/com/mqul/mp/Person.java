package com.mqul.mp;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Person implements Serializable
{
    @Column(name = "firstNames")
    private String firstNames;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "imdb_ref")
    private String imdbRef;

    public Person()
    {
        //
    }

    public Person(String firstNames, String lastName, String imdbRef)
    {
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.imdbRef = imdbRef;
    }

    public String getFirstNames()
    {
        return firstNames;
    }

    public void setFirstNames(String firstNames)
    {
        this.firstNames = firstNames;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getImdbRef()
    {
        return imdbRef;
    }

    public void setImdbRef(String imdbRef)
    {
        this.imdbRef = imdbRef;
    }
}
