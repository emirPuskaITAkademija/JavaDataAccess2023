package org.example.dao;

import org.example.model.Person;

/**
 * PersonDao as a type.
 * Marker interface/abstract class.
 */
public abstract class PersonDao implements Dao<Person> {
    private String mediaFileName;

    public PersonDao(String mediaFileName) {
        this.mediaFileName = mediaFileName;
    }

    protected String getMediaFileName() {
        return mediaFileName;
    }
}
