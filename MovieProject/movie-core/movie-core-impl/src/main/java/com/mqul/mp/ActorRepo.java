package com.mqul.mp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ActorRepo
{
    private Logger log = LoggerFactory.getLogger(ActorRepo.class);

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Actor findActorByRef(String actorImdbRef)
    {
        try
        {
            return Actor.class.cast(
                    entityManager.createQuery("SELECT a FROM Actor a WHERE a.imdbRef = :imdbRef")
                            .setParameter("imdbRef", actorImdbRef).getSingleResult()
            );
        }
        catch (NoResultException nre)
        {
            log.error("Could not find Actor with the given imdb ref [{}]", actorImdbRef);
            return null;
        }
    }

    public void addActor(Actor a)
    {
        final Actor actor = findActorByRef(a.getImdbRef());

        if(actor != null)
        {
            throw new IllegalArgumentException(
                    String.format("Person with the ref [%s] already exists", actor.getID())
            );
        }
        else
        {
            persist(a);
        }
    }

    private void persist(Actor actor)
    {
        entityManager.persist(actor);
    }
}
