package org.kulk.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.Getter;
import org.kulk.db.entities.Person;
import org.kulk.db.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: frisokulk
 * Date: 1/28/17
 * Time: 3:21 PM
 */
@Repository
@Transactional
public class UserDaoImpl extends BaseDao<User> implements UserDao {


    @PersistenceContext(unitName = "calendar")
    @Getter
    private EntityManager entityManager;

    @Override
    public List<Person> retrieveUserByName(String userName) {
	return createNamedQuery(User.FIND_BY_NAME).setParameter(1, userName).getResultList();
    }

    @Override
    public void save(User user) {
	entityManager.persist(user);
    }

}
