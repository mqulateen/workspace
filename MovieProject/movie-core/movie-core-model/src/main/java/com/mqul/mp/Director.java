package com.mqul.mp;

import javax.persistence.*;
import java.io.Serializable;

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

    public Director(String imdbRef, String firstNames, String lastName)
    {
        super(imdbRef, firstNames, lastName);
    }

    public int getID()
    {
        return id;
    }

    public DirectorDTO transferToDTO()
    {
        return new DirectorDTO(id, super.getFirstNames(), super.getLastName(), super.getImdbRef());
    }
}
