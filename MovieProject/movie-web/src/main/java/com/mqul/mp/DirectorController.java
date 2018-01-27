package com.mqul.mp;

import com.mqul.mp.models.RequestDirector;
import com.mqul.mp.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<DirectorDTO> getAllDirectors()
    {
        return directorService.getDirectors();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DirectorDTO getDirectorById(@PathVariable("id") int id)
    {
        return directorService.getDirectorById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/imdb/{ref}", method = RequestMethod.GET)
    public DirectorDTO getDirectorByRef(@PathVariable("ref") String ref)
    {
        return directorService.getDirectorByImdbRef(ref);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public DirectorDTO createDirector(@Valid @RequestBody RequestDirector requestDirector)
    {
        Objects.requireNonNull(requestDirector, "Request body cannot be empty");

        return directorService.createDirector(
                requestDirector.getFirstNames(),
                requestDirector.getLastName(),
                requestDirector.getImdbRef()
        );
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public DirectorDTO updateDirector(@PathVariable( "id" ) int id,
                                      @Valid @RequestBody RequestDirector requestDirector)
    {
        Objects.requireNonNull(getDirectorById(id), "Could not find director with ID: " + id);
        Objects.requireNonNull(requestDirector, "Request body cannot be empty");

        return directorService.updateDirector(
                id,
                requestDirector.getFirstNames(),
                requestDirector.getLastName(),
                requestDirector.getImdbRef()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDirector(@PathVariable("id") int id)
    {
        Objects.requireNonNull(getDirectorById(id), "Could not find director with ID: " + id);

        directorService.deleteDirector(id);
    }
}
