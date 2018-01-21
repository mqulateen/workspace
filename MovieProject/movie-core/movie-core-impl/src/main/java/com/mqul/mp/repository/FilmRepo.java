package com.mqul.mp.repository;

import com.mqul.mp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class FilmRepo
{
    private Logger log = LoggerFactory.getLogger(FilmRepo.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Film findFilmById(int id)
    {
        return entityManager.find(Film.class, id);
    }

    public Film findFilmByRef(String filmImdbRef)
    {
        Query query = entityManager.createQuery("SELECT f FROM Film f WHERE f.imdbRef = :imdbRef");
        query.setParameter("imdbRef", filmImdbRef);

        try
        {
            return (Film) query.getSingleResult();
        }
        catch (NoResultException ex)
        {
            log.error("Could not find Film with the given imdbRef [{}]", filmImdbRef);
            return null;
        }
    }

    public List<Film> getAllFilms()
    {
        Query q = entityManager.createQuery("SELECT f FROM Film f");

        return q.getResultList();
    }

    public void createFilm(Film film)
    {
        persist(film);
    }

    public void persist(Film film)
    {
        entityManager.persist(film);
    }

    public void deleteFilm(int filmId)
    {
        Film film = findFilmById(filmId);

        remove(film);
    }

    private void remove(Film film)
    {
        entityManager.remove(film);
    }

    public Film updateFilm(int id, String filmName, Integer filmYear, String imdbRef, Double imdbRating)
    {
        QueryBuilder qb = new QueryBuilder("UPDATE Film f");

        if(filmName != null)
        {
            qb.set("f.filmName", filmName);
        }

        if(filmYear != null)
        {
            qb.set("f.filmYear", filmYear);
        }

        if(imdbRef != null)
        {
            qb.set("f.imdbRef", imdbRef);
        }

        if(imdbRating != null)
        {
            qb.set("f.imdbRating", imdbRating);
        }

        qb.where("a.id", id);

        entityManager.createQuery(qb.build()).executeUpdate();

        return findFilmById(id);
    }
}
