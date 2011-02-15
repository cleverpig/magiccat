package org.magiccat.service;

import org.magiccat.dao.AbstractHibernateBaseDAO;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
public interface SingleDAOService<DAO extends AbstractHibernateBaseDAO<T,ID>,T,ID extends Serializable>
    extends PaginationService<T>,GenericDBOperationService<T,ID> {
}
