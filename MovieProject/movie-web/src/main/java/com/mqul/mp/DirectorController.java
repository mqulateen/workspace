package com.mqul.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/director")
public class DirectorController {

    @Autowired
    private PersonRepo repo;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Director> findAll()
    {
        return repo.getAll(PersonType.DIRECTOR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Director findById(@PathVariable("id") int id)
    {
        return repo.findDirectorById( id );
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public boolean createDirector(@RequestBody Director resource)
    {
        if (Objects.nonNull(resource))
        {
            repo.addPerson(resource);
            return true;
        }
        else
        {
            throw new NullPointerException("Incorrect data - value: " + resource);
        }
    }
}
