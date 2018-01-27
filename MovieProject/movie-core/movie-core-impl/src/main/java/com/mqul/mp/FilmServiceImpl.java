package com.mqul.mp;

import com.mqul.mp.repository.ActorRepo;
import com.mqul.mp.repository.DirectorRepo;
import com.mqul.mp.repository.FilmRepo;
import com.mqul.mp.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.mqul.mp.PersonType.ACTOR;
import static com.mqul.mp.PersonType.DIRECTOR;

@Service
public class FilmServiceImpl implements FilmService
{
    @Autowired
    private FilmRepo filmRepo;

    @Autowired
    private ActorRepo actorRepo;

    @Autowired
    private DirectorRepo directorRepo;

    @Override
    public List<FilmDTO> getAllFilms()
    {
        final List<Film> films = filmRepo.getAllFilms();

        return films.stream().map(Film::transferToDTO).collect(Collectors.toList());
    }

    @Override
    public FilmDTO getFilmById(int id)
    {
        return filmRepo.findFilmById(id).transferToDTO();
    }

    @Override
    public FilmDTO getFilmByImdbRef(String imdbRef)
    {
        final Film film = filmRepo.findFilmByRef(imdbRef);

        Objects.requireNonNull(film, "Could not find film with imdbRef: " + imdbRef);

        return film.transferToDTO();
    }

    @Override
    public List<ActorDTO> getActorsByFilm(int id)
    {
        final FilmDTO filmDTO = getFilmById(id);

        return filmDTO.getActors();
    }

    @Override
    public List<DirectorDTO> getDirectorsByFilm(int id)
    {
        final FilmDTO filmDTO = getFilmById(id);

        return filmDTO.getDirectors();
    }

    @Override
    public FilmDTO createFilm(String filmName, int filmYear, String imdbRef, double imdbRating)
    {
        //check if film already exists
        final Film existingFilm = filmRepo.findFilmByRef(imdbRef);

        Objects.requireNonNull(existingFilm, String.format("Film with imdbRef [%s] already exists", imdbRef));

        final Film film = new Film(filmName, filmYear, imdbRef, imdbRating);
        filmRepo.createFilm(film);

        return filmRepo.findFilmById(film.getId()).transferToDTO();
    }

    @Override
    @Transactional
    public FilmDTO updateFilm(int id, String filmName, Integer filmYear, String imdbRef, Double imdbRating)
    {
        final Film film = filmRepo.findFilmById(id);

        if(Objects.equals(film.getFilmName(), filmName))
            film.setFilmName(filmName);

        if(MovieUtils.notEquals(film.getFilmYear(), filmYear))
            film.setFilmYear(filmYear);

        if(MovieUtils.notEquals(film.getImdbRef(), imdbRef))
            film.setImdbRef(imdbRef);

        if(MovieUtils.notEquals(film.getImdbRating(), imdbRating))
            film.setImdbRating(imdbRating);

        return film.transferToDTO();
    }

    @Override
    @Transactional
    public FilmDTO addPersonToFilm(int filmId, int personId, PersonType type)
    {
        final Film film = filmRepo.findFilmById(filmId);

        Objects.requireNonNull(film, "Could not find Film with with ID: " + filmId);

        if(type == ACTOR)
        {
            final Actor actor = actorRepo.findActorById(personId);
            Objects.requireNonNull(actor, "Could not find Actor with with ID: " + personId);
            film.addActor(actor);
        }
        else if(type == DIRECTOR)
        {
            final Director director = directorRepo.findDirectorById(personId);
            Objects.requireNonNull(director, "Could not find Director with with ID: " + personId);
            film.addDirector(director);
        }

        return film.transferToDTO();
    }

    @Override
    @Transactional
    public FilmDTO removePersonFromFilm(int filmId, int personId, PersonType type)
    {
        final Film film = filmRepo.findFilmById(filmId);

        Objects.requireNonNull(film, "Could not find Film with with ID: " + filmId);

        if(type == ACTOR)
        {
            final Actor actor = actorRepo.findActorById(personId);
            Objects.requireNonNull(actor, "Could not find Actor with with ID: " + personId);
            film.removeActor(actor);
        }
        else if(type == DIRECTOR)
        {
            final Director director = directorRepo.findDirectorById(personId);
            Objects.requireNonNull(director, "Could not find Director with with ID: " + personId);
            film.removeDirector(director);
        }

        return film.transferToDTO();
    }

    @Override
    public void deleteFilm(int filmId)
    {
        filmRepo.deleteFilm(filmId);
    }
}
