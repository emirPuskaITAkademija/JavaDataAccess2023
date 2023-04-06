package org.example.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * POJO vs Java Bean
 * <li>
 *     1. konstruktor bez parametara
 *     2. java.io.Serializable
 *     3. get set
 *     4. public class
 * </li>
 */
public class Person implements Serializable{
    private String nationalIdentificationNumber;
    private String name;
    private String surname;
    private LocalDate birthday;

    public Person(){}

    public Person(String nationalIdentificationNumber, String name, String surname, LocalDate birthday) {
        this.nationalIdentificationNumber = nationalIdentificationNumber;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public String getNationalIdentificationNumber() {
        return nationalIdentificationNumber;
    }

    public void setNationalIdentificationNumber(String nationalIdentificationNumber) {
        this.nationalIdentificationNumber = nationalIdentificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nationalIdentificationNumber='" + nationalIdentificationNumber + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
