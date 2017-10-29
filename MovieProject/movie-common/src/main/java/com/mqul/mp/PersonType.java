package com.mqul.mp;

public enum PersonType
{
    ACTOR,
    DIRECTOR;

    public String toString()
    {
        final String type = this.name();
        final char firstLetter = type.charAt(0);
        final String afterFirstLetter = type.substring(1).toLowerCase();

        return firstLetter + afterFirstLetter;
    }
}
