package com.mqul.mp;

import com.mqul.mp.models.RequestFilm;
import com.mqul.mp.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/film")
public class FilmController
{
    @Autowired
    private FilmService filmService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<FilmDTO> getAllFilms()
    {
        return filmService.getAllFilms();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FilmDTO getFilmById(@PathVariable("id") int id)
    {
        return filmService.getFilmById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/imdb/{id}", method = RequestMethod.GET)
    public FilmDTO getFilmByRef(@PathVariable("ref") String ref)
    {
        return filmService.getFilmByImdbRef(ref);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public FilmDTO createFilm(@Valid @RequestBody RequestFilm requestFilm)
    {
        Objects.requireNonNull(requestFilm, "Request body cannot be empty");

        return filmService.createFilm(
                requestFilm.getFilmName(),
                requestFilm.getFilmYear(),
                requestFilm.getImdbRef(),
                requestFilm.getImdbRating()
        );
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public FilmDTO updateFilm(@PathVariable( "id" ) int id,
                              @Valid @RequestBody RequestFilm requestFilm)
    {
        Objects.requireNonNull(getFilmById(id), "Could not find Film with ID: " + id);
        Objects.requireNonNull(requestFilm, "Request body cannot be empty");

        return filmService.updateFilm(
                id,
                requestFilm.getFilmName(),
                requestFilm.getFilmYear(),
                requestFilm.getImdbRef(),
                requestFilm.getImdbRating()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteFilm(@PathVariable("id") int id)
    {
        Objects.requireNonNull(getFilmById(id), "Could not find Film with ID: " + id);

        filmService.deleteFilm(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/actors", method = RequestMethod.GET)
    public List<ActorDTO> getActorsByFilm(@PathVariable("id") int id)
    {
        return filmService.getActorsByFilm(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/directors", method = RequestMethod.GET)
    public List<DirectorDTO> getDirectorsByFilm(@PathVariable("id") int id)
    {
        return filmService.getDirectorsByFilm(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/actor/{actorId}", method = RequestMethod.PUT)
    public FilmDTO addActorToFilm(@PathVariable(name = "id") int id, @PathVariable(name = "actorId") int actorId)
    {
        return filmService.addPersonToFilm(id, actorId, PersonType.ACTOR);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/director/{directorId}", method = RequestMethod.PUT)
    public FilmDTO addDirectorToFilm(@PathVariable(name = "id") int id, @PathVariable(name = "directorId") int directorId)
    {
        return filmService.addPersonToFilm(id, directorId, PersonType.DIRECTOR);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/actor/{actorId}", method = RequestMethod.DELETE)
    public FilmDTO removeActorFromFilm(@PathVariable(name = "id") int id, @PathVariable(name = "actorId") int actorId)
    {
        return filmService.removePersonFromFilm(id, actorId, PersonType.ACTOR);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/director/{directorId}", method = RequestMethod.DELETE)
    public FilmDTO removeDirectorFromFilm(@PathVariable(name = "id") int id, @PathVariable(name = "directorId") int directorId)
    {
        return filmService.removePersonFromFilm(id, directorId, PersonType.DIRECTOR);
    }
}
