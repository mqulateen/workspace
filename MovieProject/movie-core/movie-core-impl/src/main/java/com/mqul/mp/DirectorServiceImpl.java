package com.mqul.mp;

import com.mqul.mp.repository.DirectorRepo;
import com.mqul.mp.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl extends AbstractPersonService<Director> implements DirectorService
{
    @Autowired
    private DirectorRepo directorRepo;

    @Override
    public DirectorDTO createDirector(String firstName, String lastName, String imdbRef)
    {
        return create(firstName, lastName, imdbRef).transferToDTO();
    }

    @Override
    public DirectorDTO getDirectorById(int id)
    {
        return getPersonById(id).transferToDTO();
    }

    @Override
    public DirectorDTO getDirectorByImdbRef(String imdbRef)
    {
        return readByRef(imdbRef).transferToDTO();
    }

    @Override
    public List<DirectorDTO> getDirectors()
    {
        final List<Director> directors = directorRepo.getAllDirectors();

        return directors.stream().map(Director::transferToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DirectorDTO updateDirector(int id, String firstName, String lastName, String imdbRef)
    {
        return update(id, firstName, lastName, imdbRef).transferToDTO();
    }

    @Override
    public void deleteDirector(int id)
    {
        directorRepo.deleteDirector(id);
    }


    //--CONCRETE METHODS FOR ABSTRACT SIGNATURES----------------------------
    @Override
    public Director getPersonByImdbRef(String imdbRef)
    {
        return directorRepo.findDirectorByRef(imdbRef);
    }

    @Override
    public Director getPersonById(int id)
    {
        return directorRepo.findDirectorById(id);
    }

    @Override
    public Director createPerson(String firstName, String lastName, String imdbRef)
    {
        final Director director = new Director(firstName, lastName, imdbRef);
        directorRepo.createDirector(director);

        return directorRepo.findDirectorByRef(imdbRef);
    }
}
