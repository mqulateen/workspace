package com.mqul.mp;

import com.mqul.mp.repository.ActorRepo;
import com.mqul.mp.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl extends AbstractPersonService<Actor> implements ActorService
{
    @Autowired
    private ActorRepo actorRepo;

    @Override
    public ActorDTO createActor(String firstName, String lastName, String imdbRef)
    {
        return create(firstName, lastName, imdbRef).transferToDTO();
    }

    @Override
    public ActorDTO getActorById(int id)
    {
        return getPersonById(id).transferToDTO();
    }

    @Override
    public ActorDTO getActorByImdbRef(String imdbRef)
    {
        return readByRef(imdbRef).transferToDTO();
    }

    @Override
    public List<ActorDTO> getActors()
    {
        final List<Actor> actors = actorRepo.getAllActors();

        return actors.stream().map(Actor::transferToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ActorDTO updateActor(int id, String firstName, String lastName, String imdbRef)
    {
        return update(id, firstName, lastName, imdbRef).transferToDTO();
    }

    @Override
    public void deleteActor(int id)
    {
        actorRepo.deleteActor(id);
    }


    //--CONCRETE METHODS FOR ABSTRACT SIGNATURES----------------------------
    @Override
    public Actor getPersonByImdbRef(String imdbRef)
    {
        return actorRepo.findActorByRef(imdbRef);
    }

    @Override
    public Actor getPersonById(int id)
    {
        return actorRepo.findActorById(id);
    }

    @Override
    public Actor createPerson(String firstName, String lastName, String imdbRef)
    {
        final Actor actor = new Actor(firstName, lastName, imdbRef);
        actorRepo.createActor(actor);

        return actorRepo.findActorByRef(imdbRef);
    }
}
