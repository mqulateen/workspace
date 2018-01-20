package com.mqul.mp;

import com.mqul.mp.models.RequestActor;
import com.mqul.mp.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/actor")
public class ActorController
{
    @Autowired
    private ActorService actorService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<ActorDTO> getAllActors()
    {
        return actorService.getActors();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ActorDTO getActorById(@PathVariable("id") int id)
    {
        return actorService.getActorById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/imdb/{ref}", method = RequestMethod.GET)
    public ActorDTO getActorByRef(@PathVariable("ref") String ref)
    {
        return actorService.getActorByImdbRef(ref);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public ActorDTO createActor(@RequestBody RequestActor requestActor)
    {
        Objects.requireNonNull(requestActor, "Request body cannot be empty");

        return actorService.createActor(
                requestActor.getFirstNames(),
                requestActor.getLastName(),
                requestActor.getImdbRef()
        );
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ActorDTO updateActor(@PathVariable( "id" ) int id, @RequestBody RequestActor requestActor)
    {
        Objects.requireNonNull(getActorById(id), "Could not final actor with ID: " + id);
        Objects.requireNonNull(requestActor, "Request body cannot be empty");

        return actorService.updateActor(
                id,
                requestActor.getFirstNames(),
                requestActor.getLastName(),
                requestActor.getImdbRef()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteActor(@PathVariable("id") int id)
    {
        Objects.requireNonNull(getActorById(id), "Could not find actor with ID: " + id);

        actorService.deleteActor(id);
    }
}
