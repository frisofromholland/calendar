package org.kulk.db.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kulk.web.components.Viewable;

import static org.kulk.db.entities.Person.FIND_BY_BIRTH_MONTH;
import static org.kulk.db.entities.Person.FIND_BY_FIRST_OR_LAST_NAME;
import static org.kulk.db.entities.Person.FIND_BY_ID;

/**
 * Created by gebruiker on 15-11-2015.
 */
@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = FIND_BY_ID, query = "SELECT p FROM Person p WHERE p.id = ?1"),
    @NamedQuery(name = FIND_BY_FIRST_OR_LAST_NAME, query = "SELECT p FROM Person p WHERE p.firstName like ?1 OR p.lastName like ?2"),
    @NamedQuery(name = FIND_BY_BIRTH_MONTH, query = "SELECT p FROM Person p")
})

public class Person implements Viewable, Serializable {

    public static final String FIND_BY_ID = "Person.findByID";
    public static final String FIND_BY_BIRTH_MONTH = "Person.findByBirthMonth";
    public static final String FIND_BY_FIRST_OR_LAST_NAME = "Person.findByFirstOrLastName";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;


    public Person(String firstName, String lastName, Date dateOfBirth) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
	return String.format("%s %s - geboortedatum: %s", firstName, lastName, new SimpleDateFormat("dd-MM-yyyy").format(dateOfBirth));
    }

    @Override
    public String viewText() {
	return toString();
    }

    @Override
    public String getIdentification() {
	return "" + id;
    }
}
