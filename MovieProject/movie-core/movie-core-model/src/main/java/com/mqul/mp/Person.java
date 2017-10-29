package com.mqul.mp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class Person implements Serializable
{
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstNames")
    private String firstNames;

    @Column(name = "lastName")
    private String lastName;

    public Person()
    {
        //
    }

    public Person(int id, String firstNames, String lastName)
    {
        this.id = id;
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

    public void setId(int id)
    {
        this.id = id;
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
