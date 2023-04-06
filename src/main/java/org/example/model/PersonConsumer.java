package org.example.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonConsumer {
    public void consumePerson(Person person) {
        System.out.println(person.toString());
    }

    public void consumeAllPersons(int number, Person... persons) {//Person[]
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    public static void main(String[] args) {
        PersonConsumer personConsumer = new PersonConsumer();
        Person p1 = new Person();
        Person p2 = new Person();
        personConsumer.consumeAllPersons(23, p1, p2);
    }
}
