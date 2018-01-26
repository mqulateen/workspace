package com.mqul.mp.repository;

import com.mqul.mp.Actor;
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
public class ActorRepo
{
    private Logger log = LoggerFactory.getLogger(ActorRepo.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Actor findActorById(int id)
    {
        return entityManager.find(Actor.class, id);
    }

    public Actor findActorByRef(String ref)
    {
        Query query = entityManager.createQuery("SELECT a FROM Actor a WHERE a.imdbRef = :imdbRef");
        query.setParameter("imdbRef", ref);

        try
        {
            return (Actor) query.getSingleResult();
        }
        catch (NoResultException ex)
        {
            log.error("Could not find Actor with the given imdbRef [{}]", ref);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Actor> getAllActors()
    {
        Query query = entityManager.createQuery("SELECT a FROM Actor a");

        return query.getResultList();
    }

    public void createActor(Actor actor)
    {
        entityManager.persist(actor);
    }

    public void deleteActor(int id)
    {
        final Actor actor = findActorById(id);

        remove(actor);
    }

    private void remove(Actor actor)
    {
        entityManager.remove(actor);
    }
}
