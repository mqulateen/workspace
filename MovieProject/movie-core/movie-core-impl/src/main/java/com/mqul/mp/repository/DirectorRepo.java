package com.mqul.mp.repository;

import com.mqul.mp.Director;
import com.mqul.mp.QueryBuilder;
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
public class DirectorRepo
{
    private Logger log = LoggerFactory.getLogger(DirectorRepo.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Director findDirectorById(int id)
    {
        return entityManager.find(Director.class, id);
    }

    public Director findDirectorByRef(String ref)
    {
        Query query = entityManager.createQuery("SELECT d FROM Director d WHERE d.imdbRef = :imdbRef");
        query.setParameter("imdbRef", ref);

        try
        {
            return (Director) query.getSingleResult();
        }
        catch (NoResultException ex)
        {
            log.error("Could not find Director with the given imdbRef [{}]", ref);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Director> getAllDirectors()
    {
        Query query = entityManager.createQuery("SELECT a FROM Director a");

        return query.getResultList();
    }

    public Director updateDirector(int id, String firstName, String lastName, String imdbRef)
    {
        QueryBuilder qb = new QueryBuilder("UPDATE Director d");

        if(firstName != null)
        {
            qb.set("d.firstNames", firstName);
        }

        if(lastName != null)
        {
            qb.set("d.lastName", lastName);
        }

        if(imdbRef != null)
        {
            qb.set("d.imdbRef", imdbRef);
        }

        qb.where("d.id", id);

        entityManager.createQuery(qb.build()).executeUpdate();

        return findDirectorById(id);
    }

    public void createDirector(Director director)
    {
        entityManager.persist(director);
    }

    public void deleteDirector(int id)
    {
        final Director director = findDirectorById(id);

        remove(director);
    }

    private void remove(Director director)
    {
        entityManager.remove(director);
    }
}
