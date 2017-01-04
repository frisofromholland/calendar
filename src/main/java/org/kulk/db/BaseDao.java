package org.kulk.db;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * User: frisokulk
 * Date: 3/8/16
 * Time: 10:03 AM
 */
public abstract class BaseDao<T> {

    protected static final String FORMAT_FOR_LIKE_CLAUSE = "%%%s%%";

    protected abstract EntityManager getEntityManager();

    protected Query createNamedQuery(String name, Object... parameters) {
	Query query = getEntityManager().createNamedQuery(name);
	addParametersToQuery(query, parameters);
	return query;
    }

    private void addParametersToQuery(final Query query, final Object[] parameters) {
	int index = 1;
	for (Object parameter : parameters) {
	    query.setParameter(index, parameter);
	    index++;
	}
    }

    protected String formatParameterForLikeClause(String parameter) {
	return String.format(FORMAT_FOR_LIKE_CLAUSE, parameter);
    }

}
