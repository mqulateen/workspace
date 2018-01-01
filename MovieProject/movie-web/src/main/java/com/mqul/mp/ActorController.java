package com.mqul.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/actor")
public class ActorController
{
    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private ActorRepo actorRepo;

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    ResponseEntity<List<Actor>> actors(@PathVariable("id") Integer filmId)
//    {
//        if(filmId == null)
//        {
//            throw new IllegalArgumentException("filmId must be set");
//        }
//
//        List<Actor> actors = repo.getActorsByFilmId(filmId);
//
//        return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);
//    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Actor> getAll()
    {
        return personRepo.getAll(PersonType.ACTOR);
    }

    @RequestMapping(value = "/{ref}", method = RequestMethod.GET)
    @ResponseBody
    public Actor getByRef(@PathVariable("ref") String ref)
    {
        return actorRepo.findActorByRef(ref);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public boolean createActor(@RequestBody Actor resource)
    {
        if (Objects.nonNull(resource))
        {
            actorRepo.addActor(resource);
            return true;
        }
        else
        {
            throw new NullPointerException("Incorrect data - value: " + resource);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable( "id" ) int id, @RequestBody Actor actor) {
//        Preconditions.checkNotNull(actor);
//        RestPreconditions.checkNotNull(repo.findActorById(actor.getId()));
//        repo.update(actor);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteActor(@PathVariable("id") int id) {
        personRepo.removePerson(id, PersonType.ACTOR);
    }
}
