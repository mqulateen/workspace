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
    private Integer id;

    @Column(name = "film_name")
    private String filmName;

    @Column(name = "imdb_id")
    private String filmId;

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
    
    public Film(int id, String filmID, String filmName, double imdbRating, int filmYear)
    {
        this.id = id;
        this.filmId = filmID;
        this.filmName = filmName;
        this.imdbRating = imdbRating;
        this.filmYear = filmYear;
        directors = new ArrayList<Director>();
        actors = new ArrayList<Actor>();
    }
    
    public Film(int id, String filmID, String filmName, double imdbRating,
                List<Director> directors, List<Actor> actors, int filmYear){
        this(id, filmID, filmName, imdbRating, filmYear);
        this.directors = directors;
        this.actors = actors;
    }

    /**   ensure the name/id values exist   **/
//    public boolean isValid(){
//        return !(Objects.isNull(filmId) || filmId.isEmpty())
//               &&!(Objects.isNull(filmName) || filmName.isEmpty());
//    }

    //+getters
    public int getId(){return id;}
    public String getFilmID(){return filmId;}
    public String getFilmName(){return filmName;}
    public double getFilmRating(){return imdbRating;}
    public int getFilmYear(){return filmYear;}
    public List<Director> getDirectorList(){return directors;}
    public List<Actor> getActorList(){return actors;}
    
    //+setters
    public void setId(int id){this.id = id;}
    public void setFilmRatig(double imdbRating){this.imdbRating = imdbRating;}
    public void setFilmYear(int filmYear){this.filmYear = filmYear;}
    public void setDirectorList(List<Director> directors){this.directors = directors;}
    public void setActorList(List<Actor> actors){this.actors = actors;}
    public void setFilmID(String filmId){this.filmId = filmId;}
    public void setFilmName(String filmName){this.filmName = filmName;}

    public FilmDTO transferToDTO()
    {
        return new FilmDTO(id, filmName, filmId, imdbRating, filmYear,
                            TransferableUtils.transferList(directors),
                            TransferableUtils.transferList(actors));
    }
}
