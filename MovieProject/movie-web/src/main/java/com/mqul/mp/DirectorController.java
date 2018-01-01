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
    private PersonRepo personRepo;

    @Autowired
    private DirectorRepo directorRepo;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Director> findAll()
    {
        return personRepo.getAll(PersonType.DIRECTOR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Director findById(@PathVariable("id") int id)
    {
        return personRepo.findDirectorById( id );
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public boolean createDirector(@RequestBody Director resource)
    {
        if (Objects.nonNull(resource))
        {
            directorRepo.addDirector(resource);
            return true;
        }
        else
        {
            throw new NullPointerException("Incorrect data - value: " + resource);
        }
    }
}
