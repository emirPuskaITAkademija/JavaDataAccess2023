package org.example.dao.xml;

import org.example.dao.DaoException;
import org.example.dao.PersonDao;
import org.example.model.Person;

import java.beans.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonXMLDao extends PersonDao {
    public PersonXMLDao() {
        super("person.xml");
    }

    @Override
    public Person readFirst() throws DaoException {
        return null;
    }

    @Override
    public List<Person> readAll() throws DaoException {
        String filename = getMediaFileName();
        try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(filename))) {
            List<Person> personList = (List<Person>) xmlDecoder.readObject();
            return personList;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public void write(Person entity) throws DaoException {

    }

    /**
     * List<Person>   -> xml
     * java.util.ArrayList -> public class public konstruktor
     * Person -> String,
     * <p>
     * LocalDate
     *
     * @param personList
     * @throws DaoException
     */
    @Override
    public void writeAll(List<Person> personList) throws DaoException {
        if (personList == null || personList.isEmpty()) {
            System.err.println("Živ bio nemoj mi slati praznu listu kako ne bih morao bezveze otvarati stream na fajl...");
            return;
        }
        String filename = getMediaFileName();
        try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(filename))) {
            LocalDateWriterDelegat localDateWriterDelegat = new LocalDateWriterDelegat();
            xmlEncoder.setPersistenceDelegate(LocalDate.class, localDateWriterDelegat);
            xmlEncoder.writeObject(personList);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private class LocalDateWriterDelegat extends PersistenceDelegate {

        @Override
        protected Expression instantiate(Object oldInstance, Encoder out) {
            LocalDate birthday = (LocalDate) oldInstance;
            return new Expression(birthday, birthday.getClass(), "parse", new Object[]{birthday.toString()});
        }
    }
}
