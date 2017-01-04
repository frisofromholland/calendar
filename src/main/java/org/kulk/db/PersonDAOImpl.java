package org.kulk.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.kulk.commons.utility.DateUtil;
import org.kulk.db.entities.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gebruiker on 3-12-2015.
 */
@Repository
@Transactional
public class PersonDAOImpl extends BaseDao<Person> implements PersonDAO {

    @PersistenceContext(unitName = "calendar")
    @Getter
    private EntityManager entityManager;

    Logger log = Logger.getLogger(PersonDAOImpl.class);


    private static List<Person> personList = new ArrayList<Person>();

    static private String birthDayFilePathAsString = "/home/frisokulk/docs/dev/calendar_application/verjaardagen.txt";

    static {
	initPersonList();
    }

    @Override
    public void save(Person person) {
	entityManager.persist(person);
    }

    @Override
    public void merge(final Person person) {
	entityManager.merge(person);

    }

    @Override
    public Person retrievePersonByID(final Long id) {
	Query query = createNamedQuery(Person.FIND_BY_ID, id);
	return (Person) query.getSingleResult();
    }


    @Override
    public List<Person> retrievePersonByName(String name) {
	return retrieveByFirstOrLastName(name);
    }

    @Override
    public List<Person> retrieveByMonthOfbirth(int monthIndex) {
	Query query = createNamedQuery(Person.FIND_BY_BIRTH_MONTH);
	return query.getResultList();
    }


    private List<Person> retrieveByFirstOrLastName(String name) {
	if (StringUtils.isEmpty(name)) {
	    return new ArrayList<>();
	}
	Query query = createNamedQuery(Person.FIND_BY_FIRST_OR_LAST_NAME, formatParameterForLikeClause(name), formatParameterForLikeClause(name));
	return query.getResultList();
    }

    /***********
     * Deprecated methods
     **********/

    @Deprecated
    private static void initPersonList() {
	try (BufferedReader reader = Files.newBufferedReader(Paths.get(birthDayFilePathAsString), Charset.defaultCharset())) {
	    String line;
	    while ((line = reader.readLine()) != null) {
		addToPersonsIfNotNull(createPersonFromCsvLine(line));
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Deprecated
    private static void addToPersonsIfNotNull(Person toBeAdded) {
	if (toBeAdded != null) {
	    personList.add(toBeAdded);
	}
    }

    @Deprecated
    private static Person createPersonFromCsvLine(String CsvInput) {
	String[] values = CsvInput.split(",");
	if (values.length == 3) {
	    return new Person(values[0], values[1], DateUtil.createDateFromString(values[2]));
	} else {
	    return null;
	}
    }

    @Deprecated
    private Person retrieveFromPersonListByName(String name) {
	for (Person person : personList) {
	    if (person != null && (StringUtils.equalsIgnoreCase(person.getFirstName(), name) || StringUtils.equalsIgnoreCase(person.getLastName(), name))) {
		return person;
	    }
	}
	return null;
    }


}
