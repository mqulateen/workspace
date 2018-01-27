package com.mqul.mp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author mqul
 *
 * director model mapping to director tbl
 */
@Table(name = "directors")
@Entity
public class Director extends Person implements Serializable, TransferableObject<DirectorDTO>
{
    @Id
    @Column(name = "director_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public Director()
    {
        //
    }

    public Director(String firstNames, String lastName, String imdbRef)
    {
        super(firstNames, lastName, imdbRef);
    }

    public int getID()
    {
        return id;
    }

    public DirectorDTO transferToDTO()
    {
        return new DirectorDTO(id, super.getFirstNames(), super.getLastName(), super.getImdbRef());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(getImdbRef(), director.getImdbRef());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
