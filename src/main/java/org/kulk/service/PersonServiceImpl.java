package org.kulk.service;

import java.util.List;
import java.util.stream.Collectors;

import org.kulk.db.PersonDAO;
import org.kulk.db.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Override
    public void save(Person person) {
	personDAO.save(person);
    }

    @Override
    public void update(final Person person) {
	personDAO.merge(person);
    }

    @Override
    public Person getPersonByID(String id) {
	return personDAO.retrievePersonByID(Long.parseLong(id));
    }

    @Override
    public List<Person> getPersonByName(String firstOrLastName) {
	return personDAO.retrievePersonByName(firstOrLastName);
    }

    @Override
    public List<Person> getPersonsByMonthOfBirth(int monthIndex) {
	return personDAO.retrieveByMonthOfbirth(monthIndex)
	    .stream()
	    .filter(person -> person.getDateOfBirth().getMonth() == monthIndex)
	    .collect(Collectors.toList());
    }

    @Override
    public String getDisplayName(Person person) {
	String displayName = "";
	if (person != null) {
	    displayName = person.getFirstName() + " " + person.getLastName();
	}
	if (person.getDateOfBirth() != null) {
	    displayName += "(" + person.getDateOfBirth().toString() + ")";
	}
	return displayName;
    }

}