package org.kulk.service;

import java.util.Collections;
import java.util.List;

import org.kulk.db.RoleDao;
import org.kulk.db.UserDao;
import org.kulk.db.entities.Role;
import org.kulk.db.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: frisokulk
 * Date: 1/28/17
 * Time: 3:20 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Override
    public void save(final User user) {
	if (userDao.retrieveUserByName(user.getUserName()).stream().findAny().isPresent()) {
	    throw new RuntimeException("Gebruikersnaam '" + user.getUserName() + "' bestaat al, kies een andere naam");
	}
	userDao.save(user);
    }

    @Override
    public void update(final User user) {

    }

    @Override
    public User getSingleUserByNameAndPassword(final String userName, final String password) {
	return new User(userName, password, true);
    }

    @Override
    public List<User> getUsersByRole(final String roleName) {
	List<User> users = Collections.EMPTY_LIST;
	users.add(new User("userName", "password", true));
	return users;
    }

    @Override
    public List<Role> allRoles() {
	return roleDao.allRoles();
    }
}
