package org.example.dao.txt;

import org.example.dao.DaoException;
import org.example.dao.PersonDao;
import org.example.model.Person;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
//List<Person>

/**
 * name,surname,nin,birthday
 * name,surname,nin,birthday
 */
public class PersonTXTDao extends PersonDao {
    public PersonTXTDao() {
        super("person.txt");
    }

    @Override
    public Person readFirst() throws DaoException {
        return null;
    }

    @Override
    public List<Person> readAll() throws DaoException {
        String filename = getMediaFileName();
        List<Person> personList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(filename))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                //DZ: isto ovo uraditi pomoću split...
                //String[] properties =  line.split(",");
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                String name = stringTokenizer.nextToken();
                String surname = stringTokenizer.nextToken();
                String nin = stringTokenizer.nextToken();
                String birthday = stringTokenizer.nextToken();
                StringTokenizer dateTokenizer = new StringTokenizer(birthday, "-");
                int year = Integer.parseInt(dateTokenizer.nextToken());
                int month = Integer.parseInt(dateTokenizer.nextToken());
                int day = Integer.parseInt(dateTokenizer.nextToken());
                LocalDate birthdayLocalDate = LocalDate.of(year, month, day);
                Person person = new Person(nin, name, surname, birthdayLocalDate);
                personList.add(person);
            }
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
        return personList;
    }

    @Override
    public void write(Person entity) throws DaoException {

    }

    @Override
    public void writeAll(List<Person> persons) throws DaoException {
        if (persons == null || persons.isEmpty()) {
            throw new DaoException("Nemoj mi molim te slati praznu list persona...");
        }
        //u konzolu NE nego u file DA
        String filename = getMediaFileName();
        //try-with-resource
        //try-catch-finnaly
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Person person : persons) {
//                String line = new StringBuilder()
//                        .append(person.getName())
//                        .append(",")
//                        .append(person.getSurname())
//                        .append(",")
//                        .append(person.getNationalIdentificationNumber())
//                        .append(",")
//                        .append(person.getBirthday().toString())
//                        .toString();
                String line = new StringJoiner(",")
                        .add(person.getName())
                        .add(person.getSurname())
                        .add(person.getNationalIdentificationNumber())
                        .add(person.getBirthday().toString())
                        .toString();
                out.println(line);
            }
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
