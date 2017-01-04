package org.kulk.db;

import java.util.List;

import org.kulk.db.entities.Person;

/**
 * Created by gebruiker on 3-12-2015.
 */
public interface PersonDAO {


    void save(Person person);

    void merge(Person person);

    Person retrievePersonByID(Long id);

    List<Person> retrievePersonByName(String name);

    List<Person> retrieveByMonthOfbirth(int monthIndex);
}
