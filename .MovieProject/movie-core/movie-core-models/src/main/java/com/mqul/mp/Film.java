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
    private String imdbRating;

    @Column(name = "film_year")
    private String filmYear;

    private List<Director> directors;
    private List<Actor> actors;
    
    public Film(){
        directors = new ArrayList<Director>();
        actors = new ArrayList<Actor>();
    }
    
    public Film(String filmID, String filmName, String imdbRating, String filmYear)
    {
        this.filmId = filmID;
        this.filmName = filmName;
        this.imdbRating = imdbRating;
        this.filmYear = filmYear;
        directors = new ArrayList<Director>();
        actors = new ArrayList<Actor>();
    }
    
    public Film(String filmID, String filmName, String imdbRating, 
            List<Director> directors, List<Actor> actors, String filmYear){
        this.filmId = filmID;
        this.filmName = filmName;
        this.imdbRating = imdbRating;
        this.directors = directors;
        this.actors = actors;
        this.filmYear = filmYear;
    }

    /**   ensure the name/id values exist   **/
//    public boolean isValid(){
//        return !(Objects.isNull(filmId) || filmId.isEmpty())
//               &&!(Objects.isNull(filmName) || filmName.isEmpty());
//    }

    //+getters
    public Integer getId(){return id;}
    public String getFilmID(){return filmId;}
    public String getFilmName(){return filmName;}
    public String getFilmRating(){return imdbRating;}
    public String getFilmYear(){return filmYear;}
    public List<Director> getDirectorList(){return directors;}
    public List<Actor> getActorList(){return actors;}
    
    //+setters
    public void setId(Integer id){this.id = id;}
    public void setFilmRatig(String imdbRating){this.imdbRating = imdbRating;}
    public void setFilmYear(String filmYear){this.filmYear = filmYear;}
    public void setDirectorList(List<Director> directors){this.directors = directors;}
    public void setActorList(List<Actor> actors){this.actors = actors;}
    public void setFilmID(String filmId){this.filmId = filmId;}
    public void setFilmName(String filmName){this.filmName = filmName;}

    @SuppressWarnings("unchecked")
    public FilmDTO transferToDTO()
    {
        return new FilmDTO(id, filmName, filmId, imdbRating, filmYear,
                            TransferableUtils.transferList(directors),
                            TransferableUtils.transferList(actors));
    }
}
