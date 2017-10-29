package com.mqul.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmRepo repo;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Film> findAll()
    {
        return repo.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Film findById(@PathVariable("id") int id)
    {
        return repo.findFilmById( id );
    }

    @RequestMapping(value = "/{id}/actors", method = RequestMethod.GET)
    @ResponseBody
    public List<Actor> findActorsByFilm(@PathVariable("id") int id)
    {
        return repo.getActorsByFilmId(id);
    }

    @RequestMapping(value = "/{id}/directors", method = RequestMethod.GET)
    @ResponseBody
    public List<Director> findDirectorsByFilm(@PathVariable("id") int id)
    {
        return repo.getDirectorsByFilmId(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public boolean createFilm(@RequestBody Film resource)
    {
        if (Objects.nonNull(resource))
        {
            repo.addNewFilm(resource);
            return true;
        }
        else
        {
            throw new NullPointerException("Incorrect data - value: " + resource);
        }
    }
}
