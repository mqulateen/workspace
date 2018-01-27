package com.mqul.mp.models;

import javax.validation.constraints.Size;

public class RequestActor
{
    private String firstNames;
    private String lastName;

    @Size(max = 7)
    private String imdbRef;

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImdbRef() {
        return imdbRef;
    }

    public void setImdbRef(String imdbRef) {
        this.imdbRef = imdbRef;
    }
}
