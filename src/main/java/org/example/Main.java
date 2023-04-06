package org.example;

import org.example.dao.PersonDaoExecutor;
import org.example.dao.ser.PersonSerializationDao;
import org.example.dao.txt.PersonTXTDao;
import org.example.model.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Nekoliko važnih stvari kada je u pitanju čuvanje podataka u fajlovima:
 * <li>1. XML</li>
 * <li>2. JSON</li>
 * <li>3. Binarnoj serijalizaciji </li>
 * <li>4. txt</li>
 */
public class Main {
    public static void main(String[] args) {
        List<Person> personList = List.of(
                new Person("1213324", "Mirsad", "Škandro", LocalDate.of(1990, 1, 1)),
                new Person("1111111", "Kenan", "Bukva", LocalDate.of(1988, 1, 1)),
                new Person("222222", "Anja", "Tešanović", LocalDate.of(1999, 1, 1)),
                new Person("3333333", "Slađana", "Jokić", LocalDate.of(2003, 1, 1)),
                new Person("44444444", "Rahima", "Granulo", LocalDate.of(1990, 1, 1)),
                new Person("5555555", "Amela", "Nožinović", LocalDate.of(2004, 1, 1)),
                new Person("66666666", "Eldar", "Pediša", LocalDate.of(1995, 1, 1))
                );
        PersonDaoExecutor personDaoExecutor = new PersonDaoExecutor(new PersonTXTDao());

        personDaoExecutor.setPersonDao(new PersonSerializationDao());
        personDaoExecutor.saveAllPersons(personList);
        personDaoExecutor.findAllPersons().forEach(System.out::println);
//        List<Person> personList = personDaoExecutor.findAllPersons();
        //personList -> ArrayList, List, Collection, Iterable<Person>
        /**
         * Pristup 1
         */
//        for(int i = 0; i<personList.size(); i++){
//            Person person = personList.get(i);
//            if(person.getName().equalsIgnoreCase("kenan")){
//                System.out.println("Uklanjam Kenana po indeksu....");
//                personList.remove(i);
//                i--;
//            }else{
//                System.out.println(person);
//            }
//        }

        /**
         * Pristup 2
         */
//        for(Person person : personList){
//            if(person.getName().equalsIgnoreCase("kenan")){
//                personList.remove(person);  NE MOŽE
//            }
//            System.out.println(person);
//        }
//        Iterator<Person> personIterator = personList.iterator();
//        while (personIterator.hasNext()){
//            Person person = personIterator.next();
//            if(person.getName().equalsIgnoreCase("kenan")){
//                personIterator.remove();
//            }else{
//                System.out.println(person);
//            }
//        }

        /**
         * Pristup 3
         */
//        Main main = new Main();
////        PersonConsumer personConsumer = main.new PersonConsumer();
//        PersonConsumer personConsumer = new PersonConsumer();
//        personList.forEach(personConsumer);
//        personList.forEach(new Consumer<Person>() {
//            @Override
//            public void accept(Person person) {
//                System.out.println(person);
//            }
//        });
//        personList.forEach(p -> System.out.println(p));
        //personList.stream().forEach(System.out::println);
        /*
         <li>1. izvor stream
         <li>2. međuoperacije nad streamom
         <li>3. terminirajuća operacija ili metoda
         */
//        PersonTester personTester = new PersonTester();
//        PersonConsumer personConsumer = new PersonConsumer();
//        personList
//                .stream()
//                .filter(p->!p.getName().equalsIgnoreCase("kenan"))
//                .forEach(p-> System.out.println(p));
        ;
    }

    private static class PersonTester implements Predicate<Person> {
        @Override
        public boolean test(Person person) {
            System.out.println("Ko si..." + person.getName());
            return !person.getName().equalsIgnoreCase("kenan");
        }
    }

    private static class PersonConsumer implements Consumer<Person> {
        @Override
        public void accept(Person person) {
            System.out.println(person);
        }
    }
}