package com.mqul.mp;

import com.mqul.mp.repository.ActorRepo;
import com.mqul.mp.repository.DirectorRepo;
import com.mqul.mp.repository.FilmRepo;
import com.mqul.mp.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

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

        if(Objects.isNull(film))
        {
            throw new IllegalArgumentException("Could not find film with imdbRef: " + imdbRef);
        }

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
        if(Objects.nonNull(getFilmByImdbRef(imdbRef)))
        {
            final String errorMessage = String.format("Film with imdbRef [%s] already exists", imdbRef);
            throw new IllegalArgumentException(errorMessage);
        }

        final Film film = new Film(filmName, filmYear, imdbRef, imdbRating);
        filmRepo.createFilm(film);

        final FilmDTO filmDTO = filmRepo.findFilmById(film.getId()).transferToDTO();

        return filmDTO;
    }

    @Override
    public FilmDTO updateFilm(int id, String filmName, Integer filmYear, String imdbRef, Double imdbRating)
    {
        if(isNull(filmName) && isNull(filmYear) && isNull(imdbRef) && isNull(imdbRating))
            throw new IllegalArgumentException("Atleast one updatable field must be present");

        return filmRepo.updateFilm(id, filmName, filmYear, imdbRef, imdbRating).transferToDTO();
    }

    @Override
    public FilmDTO addActorToFilm(int filmId, int actorId)
    {
        final Film film = filmRepo.findFilmById(filmId);
        final Actor actor = actorRepo.findActorById(actorId);

        if(Objects.isNull(film))
        {
            throw new IllegalArgumentException("Could not find Film with with ID: " + filmId);
        }

        if(Objects.isNull(actor))
        {
            throw new IllegalArgumentException("Could not find Actor with with ID: " + actorId);
        }

        film.getActors().add(actor);

        return film.transferToDTO();
    }

    @Override
    public FilmDTO addDirectorToFilm(int filmId, int directorId)
    {
        final Film film = filmRepo.findFilmById(filmId);
        final Director director = directorRepo.findDirectorById(directorId);

        if(Objects.isNull(film))
        {
            throw new IllegalArgumentException("Could not find Film with with ID: " + filmId);
        }

        if(Objects.isNull(director))
        {
            throw new IllegalArgumentException("Could not find Director with with ID: " + directorId);
        }

        film.getDirectors().add(director);

        return film.transferToDTO();
    }

    @Override
    public FilmDTO addActorsToFilm(int filmId, List<Integer> actorIds)
    {
        return null;
    }

    @Override
    public FilmDTO addDirectorsToFilm(int filmId, List<Integer> directorIds)
    {
        return null;
    }

    @Override
    public FilmDTO removeActorFromFilm(int filmId, int actorId)
    {
        final Film film = filmRepo.findFilmById(filmId);
        final Actor actor = actorRepo.findActorById(actorId);

        if(Objects.isNull(film))
        {
            throw new IllegalArgumentException("Could not find Film with with ID: " + filmId);
        }

        if(Objects.isNull(actor))
        {
            throw new IllegalArgumentException("Could not find Actor with with ID: " + actorId);
        }

        final Director filmsDirector =
                film.getDirectors().stream().filter(d -> d.getID() == actorId).findFirst().orElse(null);

        if(filmsDirector == null)
        {
            throw new IllegalArgumentException(String.format("Actor [%s] is not associated with Film [%s]", actorId, filmId));
        }

        film.getActors().remove(actor);

        return film.transferToDTO();
    }

    @Override
    public FilmDTO removeDirectorFromFilm(int filmId, int directorId)
    {
        final Film film = filmRepo.findFilmById(filmId);
        final Director director = directorRepo.findDirectorById(directorId);

        if(Objects.isNull(film))
        {
            throw new IllegalArgumentException("Could not find Film with with ID: " + filmId);
        }

        if(Objects.isNull(director))
        {
            throw new IllegalArgumentException("Could not find Director with with ID: " + directorId);
        }

        final Director filmsDirector =
                film.getDirectors().stream().filter(d -> d.getID() == directorId).findFirst().orElse(null);

        if(filmsDirector == null)
        {
            throw new IllegalArgumentException(String.format("Director [%s] is not associated with Film [%s]", directorId, filmId));
        }

        film.getDirectors().remove(director);

        return film.transferToDTO();
    }

    @Override
    public void deleteFilm(int filmId)
    {
        filmRepo.deleteFilm(filmId);
    }
}
