package org.kulk.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.Getter;
import org.kulk.db.entities.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: frisokulk
 * Date: 3/14/17
 * Time: 1:40 PM
 */
@Repository
@Transactional
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {

    @PersistenceContext(unitName = "calendar")
    @Getter
    private EntityManager entityManager;


    @Override
    public List<Role> allRoles() {
	return entityManager.createNamedQuery(Role.FIND_ALL).getResultList();
    }

}
