package com.mqul.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private PersonRepo repo;

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
    public List<Actor> findAll()
    {
        return repo.getAll(PersonType.ACTOR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Actor findById(@PathVariable("id") int id)
    {
        return repo.findActorById( id );
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public boolean createActor(@RequestBody Actor resource)
    {
        if (Objects.nonNull(resource))
        {
            repo.addNewActor(resource);
            return true;
        }
        else
        {
            throw new NullPointerException("Incorrect data - value: " + resource);
        }
    }
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable( "id" ) Long id, @RequestBody Foo resource) {
//        Preconditions.checkNotNull(resource);
//        RestPreconditions.checkNotNull(service.getById( resource.getId()));
//        service.update(resource);
//    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteActor(@PathVariable("id") Long id) {
//        repo.deleteActorById(id);
//    }
}
