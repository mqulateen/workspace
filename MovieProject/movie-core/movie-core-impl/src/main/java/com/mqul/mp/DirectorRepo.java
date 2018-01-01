package com.mqul.mp;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Repository
@Transactional
public class DirectorRepo
{
    private org.slf4j.Logger log = LoggerFactory.getLogger(DirectorRepo.class);

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Director findDirectorByRef(String directorImdbRef)
    {
        try
        {
            return Director.class.cast(
                    entityManager.createQuery("SELECT d FROM Director d WHERE d.imdbRef = :imdbRef")
                            .setParameter("imdbRef", directorImdbRef).getSingleResult()
            );
        }
        catch (NoResultException nre)
        {
            log.error("Could not find Director with the given imdb ref [{}]", directorImdbRef);
            return null;
        }
    }

    public void addDirector(Director d)
    {
        final Director director = findDirectorByRef(d.getImdbRef());

        if(director != null)
        {
            throw new IllegalArgumentException(
                    String.format("Person with the ref [%s] already exists", director.getID())
            );
        }
        else
        {
            persist(d);
        }
    }

    private void persist(Director director)
    {
        entityManager.persist(director);
    }
}
