package com.mqul.mp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class FilmRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public Film findFilmById(int id)
    {
        return entityManager.find(Film.class, id);
    }

    public List getAll()
    {
        Query q = entityManager.createQuery("SELECT f FROM Film f");

        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Actor> getActorsByFilmId(int filmId)
    {
        QueryBuilder qb = new QueryBuilder("SELECT a FROM Actor a, LookupFilmActors lp, Film f");
        String query = qb.where("a.id", "lp.actorId")
                .where("f.id", "lp.filmId")
                .where("f.id", ":filmId")
                .build();

        Query q = entityManager.createQuery(query);
        q.setParameter("filmId", filmId);

        return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<Director> getDirectorsByFilmId(int filmId)
    {
        QueryBuilder qb = new QueryBuilder("SELECT a FROM Director a, LookupFilmDirectors lp, Film f");
        String query = qb.where("a.id", "lp.directorId")
                .where("f.id", "lp.filmId")
                .where("f.id", ":filmId")
                .build();

        Query q = entityManager.createQuery(query);
        q.setParameter("filmId", filmId);

        return q.getResultList();
    }

    public void addNewFilm(Film film)
    {
        Film tmpFilm = findFilmById(film.getId());

        if(tmpFilm != null)
        {
            throw new IllegalArgumentException(
                    String.format("Film with the id [%s] already exists", film.getId())
            );
        }
        else
        {
            persist(film);
        }
    }

    public void removeFilm(int filmId)
    {
        Film film = findFilmById(filmId);

        if(film == null)
            throw new IllegalArgumentException(
                    String.format("Could not find a Film with the id [%d]", filmId)
            );

        entityManager.remove(film);
    }

    public void persist(Film film)
    {
        entityManager.persist(film);
    }
}
