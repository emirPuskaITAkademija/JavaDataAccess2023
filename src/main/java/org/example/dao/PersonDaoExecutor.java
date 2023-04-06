package org.example.dao;

import org.example.dao.txt.PersonTXTDao;
import org.example.model.Person;

import java.util.Collections;
import java.util.List;

/**
 * PersonDao
 * <li>1. {@link org.example.dao.txt.PersonTXTDao}</li>
 * <li>2. {@link org.example.dao.xml.PersonXMLDao}</li>
 * <li>3. {@link org.example.dao.ser.PersonSerializationDao}</li>
 */
public class PersonDaoExecutor{
    /**
     * <li>
     *     Favor composition over inheritance.
     * </li>
     * <li>
     *     Depend upon abstraction not upon concrete implementation.
     * </li>
     */
    private PersonDao personDao;

    public PersonDaoExecutor(PersonDao personDao){
        this.personDao = personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void saveAllPersons(List<Person> personList)  {
        try{
            personDao.writeAll(personList);
        }catch (DaoException exception){
            System.err.println(exception.getMessage());
        }
    }

    public List<Person> findAllPersons(){
        try {
            List<Person> personList = personDao.readAll();
            return personList;
        } catch (DaoException e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
    }
}
