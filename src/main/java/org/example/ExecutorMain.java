package org.example;

import org.example.dao.PersonDaoExecutor;
import org.example.dao.json.PersonJSONDao;
import org.example.dao.ser.PersonSerializationDao;
import org.example.dao.xml.PersonXMLDao;
import org.example.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Pitanje perzistencije je pitanje trajnog čuvanja podataka.
 * <li>.person.txt</li>
 */
public class ExecutorMain {
    public static void main(String[] args) {
        //java.util.Arrays$ArrayList
        //java.util.ArrayList
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("1213324", "Mirsad", "Škandro", LocalDate.of(1990, 1, 1)));
        personList.add(new Person("1111111", "Kenan", "Bukva", LocalDate.of(1988, 1, 1)));
        personList.add(new Person("222222", "Anja", "Tešanović", LocalDate.of(1999, 1, 1)));
        personList.add(new Person("3333333", "Slađana", "Jokić", LocalDate.of(2003, 1, 1)));
        personList.add(new Person("44444444", "Rahima", "Granulo", LocalDate.of(1990, 1, 1)));
        personList.add(new Person("5555555", "Amela", "Nožinović", LocalDate.of(2004, 1, 1)));
        personList.add(new Person("66666666", "Eldar", "Pediša", LocalDate.of(1995, 1, 1)));

        //PersonDaoExecutor -> execution strategije snimanja personList u fajlove
        PersonDaoExecutor personDaoExecutor = new PersonDaoExecutor(new PersonSerializationDao());
        personDaoExecutor.setPersonDao(new PersonXMLDao());
        personDaoExecutor.setPersonDao(new PersonJSONDao());
        personDaoExecutor.saveAllPersons(personList);
        List<Person> personListReaded = personDaoExecutor.findAllPersons();
        for(Person person : personListReaded){
            System.out.println(person);
        }
    }
}
