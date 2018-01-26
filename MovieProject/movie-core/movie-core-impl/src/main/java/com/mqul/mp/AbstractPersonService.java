package com.mqul.mp;

import java.util.Objects;

public abstract class AbstractPersonService <T extends Person>
{
    public T create(String firstName, String lastName, String imdbRef)
    {
        if(Objects.nonNull(getPersonByImdbRef(imdbRef)))
        {
            final String errorMessage = String.format("Person with imdbRef [%s] already exists", imdbRef);
            throw new IllegalArgumentException(errorMessage);
        }

        return createPerson(firstName, lastName, imdbRef);
    }

    public T readByRef(String imdbRef)
    {
        final T person = getPersonByImdbRef(imdbRef);

        if(Objects.isNull(person))
        {
            throw new IllegalArgumentException("Could not find Person with imdbRef: " + imdbRef);
        }

        return person;
    }

    public T update(int id, String firstName, String lastName, String imdbRef)
    {
        final T person = getPersonById(id);

        Objects.requireNonNull(person, "Couldn't not find Person with ID: " + id);

        if(Utils.notEquals(person.getFirstNames(), firstName))
            person.setFirstNames(firstName);

        if(Utils.notEquals(person.getLastName(), lastName))
            person.setLastName(lastName);

        if(Utils.notEquals(person.getImdbRef(), imdbRef))
            person.setImdbRef(imdbRef);

        return person;
    }

    public abstract T getPersonByImdbRef(String imdbRef);
    public abstract T getPersonById(int id);
    public abstract T createPerson(String firstName, String lastName, String imdbRef);
}
