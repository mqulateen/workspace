package com.mqul.mp;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mqul
 *
 * film model mapping to film tbl
 */
@Table(name = "films")
@Entity
public class Film implements Serializable, TransferableObject<FilmDTO>
{
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "film_name")
    private String filmName;

    @Column(name = "imdb_ref")
    private String imdbRef;

    @Column(name = "imdb_rating")
    private double imdbRating;

    @Column(name = "film_year")
    private int filmYear;

    @Transient
    private List<Director> directors;

    @Transient
    private List<Actor> actors;
    
    public Film(){
        directors = new ArrayList<Director>();
        actors = new ArrayList<Actor>();
    }
    
    public Film(String imdbRef, String filmName, double imdbRating, int filmYear)
    {
        this.imdbRef = imdbRef;
        this.filmName = filmName;
        this.imdbRating = imdbRating;
        this.filmYear = filmYear;
        directors = new ArrayList<Director>();
        actors = new ArrayList<Actor>();
    }
    
    public Film(String imdbRef, String filmName, double imdbRating,
                List<Director> directors, List<Actor> actors, int filmYear){
        this(imdbRef, filmName, imdbRating, filmYear);
        this.directors = directors;
        this.actors = actors;
    }

    public int getId()
    {
        return id;
    }

    public String getFilmName()
    {
        return filmName;
    }

    public void setFilmName(String filmName)
    {
        this.filmName = filmName;
    }

    public String getImdbRef()
    {
        return imdbRef;
    }

    public void setImdbRef(String imdbRef)
    {
        this.imdbRef = imdbRef;
    }

    public double getImdbRating()
    {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating)
    {
        this.imdbRating = imdbRating;
    }

    public int getFilmYear()
    {
        return filmYear;
    }

    public void setFilmYear(int filmYear)
    {
        this.filmYear = filmYear;
    }

    public List<Director> getDirectors()
    {
        return directors;
    }

    public void setDirectors(List<Director> directors)
    {
        this.directors = directors;
    }

    public List<Actor> getActors()
    {
        return actors;
    }

    public void setActors(List<Actor> actors)
    {
        this.actors = actors;
    }

    /**   ensure the name/id values exist   **/
//    public boolean isValid(){
//        return !(Objects.isNull(filmId) || filmId.isEmpty())
//               &&!(Objects.isNull(filmName) || filmName.isEmpty());
//    }



    public FilmDTO transferToDTO()
    {
        return new FilmDTO(id, filmName, imdbRef, imdbRating, filmYear,
                            TransferableUtils.transferList(directors),
                            TransferableUtils.transferList(actors));
    }
}
