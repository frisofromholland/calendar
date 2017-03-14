package org.kulk.db;

import java.util.List;

import org.kulk.db.entities.Person;
import org.kulk.db.entities.User;

/**
 * User: frisokulk
 * Date: 1/28/17
 * Time: 3:22 PM
 */
public interface UserDao {

    List<Person> retrieveUserByName(String userName);

    void save(User user);

}
