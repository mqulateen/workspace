package com.mqul.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class GetController {

    @Autowired
    private PersonRepo personRepo;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<List<Actor>> actors(@PathVariable("id") Integer filmId)
    {
        if(filmId == null)
        {
            throw new IllegalArgumentException("filmId must be set");
        }

        //List<Actor> actors = personRepo.getActorsByFilmId(filmId);
        List<Actor> testList = Arrays.asList(
                new Actor("nn", "dsetyj", "sthd"),
                new Actor("nfdgn", "jyuds", "fghsd"),
                new Actor("ngrtegn", "dregs", "sd"),
                new Actor("nren", "dregs", "srthed"),
                new Actor("nregn", "dregs", "s54yd")
                                            );
        return new ResponseEntity<List<Actor>>(testList, HttpStatus.OK);
    }
}
