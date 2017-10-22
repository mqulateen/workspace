package com.mqul.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/actors")
class FooController {

    @Autowired
    private PersonRepo service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Actor> findAll() {
        return service.getActorsByFilmId(1);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Actor findOne(@PathVariable("id") int id) {
        return service.findActorById( id );
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public boolean create(@RequestBody Actor resource) {
        Objects.nonNull(resource);
        service.addNewActor(resource);
        return true;
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable( "id" ) Long id, @RequestBody Foo resource) {
//        Preconditions.checkNotNull(resource);
//        RestPreconditions.checkNotNull(service.getById( resource.getId()));
//        service.update(resource);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@PathVariable("id") Long id) {
//        service.deleteById(id);
//    }

}