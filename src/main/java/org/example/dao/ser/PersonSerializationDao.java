package org.example.dao.ser;

import org.example.dao.DaoException;
import org.example.dao.PersonDao;
import org.example.model.Person;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class PersonSerializationDao extends PersonDao {
    public PersonSerializationDao() {
        super("person.ser");
    }

    @Override
    public Person readFirst() throws DaoException {
        return null;
    }

    @Override
    public List<Person> readAll() throws DaoException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getMediaFileName()))) {
            return (List<Person>) ois.readObject();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Collections.emptyList();
    }

    @Override
    public void write(Person entity) throws DaoException {

    }

    @Override
    public void writeAll(List<Person> entites) throws DaoException {
        //byte, char , line, ...
        String filename = getMediaFileName();
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(filename))) {
            ous.writeObject(entites);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
