package com.mqul.mp;

import com.mqul.mp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class GetController {

    @Autowired
    private PersonRepo personRepo;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Actor>> actors(@PathVariable Integer filmId)
    {
        if(filmId == null)
        {
            throw new IllegalArgumentException("filmId must be set");
        }

        List<Actor> actors = personRepo.getActorsByFilmId(filmId);

        return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);
    }
}
