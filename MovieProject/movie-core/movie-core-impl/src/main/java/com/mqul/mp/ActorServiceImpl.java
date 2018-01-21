package com.mqul.mp;

import com.mqul.mp.repository.ActorRepo;
import com.mqul.mp.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ActorServiceImpl implements ActorService
{
    @Autowired
    private ActorRepo actorRepo;

    @Override
    public ActorDTO createActor(String firstName, String lastName, String imdbRef)
    {
        if(Objects.nonNull(getActorByImdbRef(imdbRef)))
        {
            final String errorMessage = String.format("Actor with imdbRef [%s] already exists", imdbRef);
            throw new IllegalArgumentException(errorMessage);
        }

        final Actor actor = new Actor(firstName, lastName, imdbRef);
        actorRepo.createActor(actor);

        final ActorDTO actorDTO = actorRepo.findActorById(actor.getID()).transferToDTO();

        return actorDTO;
    }

    @Override
    public ActorDTO getActorById(int id)
    {
        return actorRepo.findActorById(id).transferToDTO();
    }

    @Override
    public ActorDTO getActorByImdbRef(String imdbRef)
    {
        final Actor actor = actorRepo.findActorByRef(imdbRef);

        if(Objects.isNull(actor))
        {
            throw new IllegalArgumentException("Could not find Actor with imdbRef: " + imdbRef);
        }

        return actor.transferToDTO();
    }

    @Override
    public List<ActorDTO> getActors()
    {
        final List<Actor> actors = actorRepo.getAllActors();

        return actors.stream().map(Actor::transferToDTO).collect(Collectors.toList());
    }

    @Override
    public ActorDTO updateActor(int id, String firstName, String lastName, String imdbRef)
    {
        if(isNull(firstName) && isNull(lastName) && isNull(imdbRef))
            throw new IllegalArgumentException("Atleast one updatable field must be present");

        return actorRepo.updateActor(id, firstName, lastName, imdbRef).transferToDTO();
    }

    @Override
    public void deleteActor(int id)
    {
        actorRepo.deleteActor(id);
    }
}
