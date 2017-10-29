package com.mqul.mp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PersonRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public Actor findActorById(int id)
    {
        return entityManager.find(Actor.class, id);
    }

    public Director findDirectorById(int id)
    {
        return entityManager.find(Director.class, id);
    }

    public void addNewActor(Actor actor)
    {
        Actor tmpActor = findActorById(actor.getId());

        if(tmpActor != null)
        {
            throw new IllegalArgumentException(
                    String.format("Actor with the id [%s] already exists", actor.getId())
            );
        }
        else
        {
            persist(actor);
        }
    }

    public void addNewDirector(Director director)
    {
        Director tmpDirector = findDirectorById(director.getId());

        if(tmpDirector != null)
        {
            throw new IllegalArgumentException(
                    String.format("Actor with the id [%s] already exists", director.getId())
            );
        }
        else
        {
            persist(director);
        }
    }

    public void removeActor(int actorId)
    {
        Actor actor = findActorById(actorId);

        if(actor == null)
            throw new IllegalArgumentException(
                    String.format("Could not find an Actor with the id [%d]", actorId)
            );

        entityManager.remove(actor);
    }

    public void removeDirector(int directorId)
    {
        Director director = findDirectorById(directorId);

        if(director == null)
            throw new IllegalArgumentException(
                    String.format("Could not find a Director with the id [%d]", directorId)
            );

        entityManager.remove(director);
    }

    public List getAll(PersonType type)
    {
        final String table = type.toString();

        Query q = entityManager.createQuery(String.format("SELECT p FROM %s p", table));

        return q.getResultList();
    }

    private void persist(Actor actor)
    {
        entityManager.persist(actor);
    }

    private void persist(Director director)
    {
        entityManager.persist(director);
    }
}
