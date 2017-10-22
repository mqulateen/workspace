package com.mqul.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.UnexpectedTypeException;
import java.security.InvalidParameterException;
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

    public List getAll(PersonType type)
    {
        final String table;
        switch (type)
        {
            case ACTOR:
                table = "Actor";
                break;
            case DIRECTOR:
                table = "Director";
                break;
            default:
                throw new InvalidParameterException(String.format("Unsupported PersonType [%s]", type.name()));
        }

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
