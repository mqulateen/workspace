package com.mqul.mp;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Person implements Serializable
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "firstNames")
    private String firstNames;

    @Column(name = "lastName")
    private String lastName;

    public Person()
    {
        //
    }

    public Person(String firstNames, String lastName)
    {
        this.firstNames = firstNames;
        this.lastName = lastName;
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

    public void setFirstNames(String firstNames)
    {
        this.firstNames = firstNames;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
