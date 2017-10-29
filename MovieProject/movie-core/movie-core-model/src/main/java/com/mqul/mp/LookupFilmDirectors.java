package com.mqul.mp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mqul
 * 
 * director model mapping to director tbl
 */
@Table(name = "Lookup_Film_Directors")
@Entity
public class LookupFilmDirectors {

    @Id
    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "id")
    private Integer directorId;

    public LookupFilmDirectors()
    {
        //
    }

    public LookupFilmDirectors(Integer filmId, Integer directorId)
    {
        this.filmId = filmId;
        this.directorId = directorId;
    }

    public Integer getFilmId(){return filmId;}

    public void setFilmId(Integer id){this.filmId = id;}

    public Integer getDirectorId(){return directorId;}

    public void setDirectorId(Integer id){this.directorId = id;}
}