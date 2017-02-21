package org.kulk.service;

import java.util.List;

import org.kulk.db.entities.User;

/**
 * User: frisokulk
 * Date: 1/28/17
 * Time: 3:19 PM
 */
public interface UserService {

    void save(User user);

    void update(User user);

    User getSingleUserByNameAndPassword(String userName, String password);

    List<User> getUsersByRole(String roleName);


}
