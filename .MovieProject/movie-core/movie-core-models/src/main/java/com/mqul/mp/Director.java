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
public class Director implements Serializable, TransferableObject<DirectorDTO>
{
    @Id
    @Column(name = "director_id")
    private Integer id;

    @Column(name = "imdb_id")
    private String directorID;

    @Column(name = "director_firstNames")
    private String firstNames;

    @Column(name = "director_lastName")
    private String lastName;

    public Director()
    {
        //
    }

    public Director(String directorID, String firstNames, String lastName)
    {
        this.directorID = directorID;
        this.firstNames = firstNames;
        this.lastName = lastName;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id){this.id = id;}

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DirectorDTO transferToDTO()
    {
        return new DirectorDTO(id, directorID, firstNames, lastName);
    }
}
