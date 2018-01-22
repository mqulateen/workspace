package com.mqul.mp;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Fetch;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @JoinTable(name = "films_directors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    @ManyToMany(fetch=FetchType.LAZY)
    private List<Director> directors;

    @JoinTable(name = "films_actors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @ManyToMany(fetch=FetchType.LAZY)
    private List<Actor> actors;
    
    public Film()
    {
        //
    }
    
    public Film(String filmName, int filmYear, String imdbRef, double imdbRating)
    {
        this.filmName = filmName;
        this.filmYear = filmYear;
        this.imdbRef = imdbRef;
        this.imdbRating = imdbRating;
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

    public void addDirector(Director director)
    {
        this.directors.add(director);
    }

    public void removeDirector(Director director)
    {
        this.directors.remove(director);
    }

    public void setDirectors(List<Director> directors)
    {
        this.directors = directors;
    }

    public List<Actor> getActors()
    {
        return actors;
    }

    public void addActor(Actor actor)
    {
        this.actors.add(actor);
    }

    public void removeActor(Actor actor)
    {
        this.actors.remove(actor);
    }

    public void setActors(List<Actor> actors)
    {
        this.actors = actors;
    }

    public FilmDTO transferToDTO()
    {
        return new FilmDTO(id, filmName, imdbRef, imdbRating, filmYear,
                            TransferableUtils.transferList(directors),
                            TransferableUtils.transferList(actors));
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(filmName, film.filmName) &&
                Objects.equals(imdbRef, film.imdbRef);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(filmName, imdbRef);
    }
}
