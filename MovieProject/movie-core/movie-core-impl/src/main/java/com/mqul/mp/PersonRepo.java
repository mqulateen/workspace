package com.mqul.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PersonRepo
{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ActorRepo actorRepo;

    @Autowired
    private DirectorRepo directorRepo;

    public Actor findActorById(int id)
    {
        return entityManager.find(Actor.class, id);
    }

    public Director findDirectorById(int id)
    {
        return entityManager.find(Director.class, id);
    }

    public void removePerson(int personId, PersonType type)
    {
        final Person p;

        switch(type)
        {
            case ACTOR:
                p = findActorById(personId);
                break;
            case DIRECTOR:
                p = findDirectorById(personId);
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Could not find Person with the id [%d]", personId)
                );
        }

        entityManager.remove(p);
    }

    public List getAll(PersonType type)
    {
        final String table = type.toString();

        Query q = entityManager.createQuery(String.format("SELECT p FROM %s p", table));

        return q.getResultList();
    }

    public void updatePerson(Person person)
    {
        if(person instanceof Actor)
            updateActor(((Actor) person));
        else if(person instanceof Director)
            updateDirector(((Director) person));
    }

    private void updateActor(Actor updatedActor)
    {
        Actor actor = actorRepo.findActorByRef(updatedActor.getImdbRef());

        if(updatedActor.getFirstNames() != null)
            actor.setFirstNames(updatedActor.getFirstNames());

        if(updatedActor.getLastName() != null)
            actor.setLastName(updatedActor.getLastName());
    }

    private void updateDirector(Director updatedDirector)
    {
        Director director = directorRepo.findDirectorByRef(updatedDirector.getImdbRef());

        if(updatedDirector.getFirstNames() != null)
            director.setFirstNames(updatedDirector.getFirstNames());

        if(updatedDirector.getLastName() != null)
            director.setLastName(updatedDirector.getLastName());
    }

    private void persist(Person person)
    {
        entityManager.persist(person);
    }
}
