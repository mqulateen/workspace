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
    @Column(name = "imdb_id")
    private String directorID;

    public Director()
    {
        //
    }

    public Director(int id, String directorID, String firstNames, String lastName)
    {
        super(id, firstNames, lastName);
        this.directorID = directorID;
    }

    public String getDirectorID()
    {
        return directorID;
    }

    public void setDirectorID(String directorID)
    {
        this.directorID = directorID;
    }

    public DirectorDTO transferToDTO()
    {
        return new DirectorDTO(super.getId(), directorID, super.getFirstNames(), super.getLastName());
    }
}
