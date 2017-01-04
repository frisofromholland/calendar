package org.kulk.service;


import java.util.List;

import org.kulk.db.entities.Person;

public interface PersonService {

    void save(Person person);

    void update(Person person);

    Person getPersonByID(String id);

    List<Person> getPersonByName(String firstOrLastName);

    List<Person> getPersonsByMonthOfBirth(int monthIndex);

    String getDisplayName(Person person);

}
