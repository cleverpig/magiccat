package org.magiccat.service.impl;

import org.magiccat.dao.AbstractHibernateBaseDAO;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.service.SingleDAOService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-15
 * Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSingleDAOService<DAO extends AbstractHibernateBaseDAO<T,ID>,T,ID extends Serializable>
    implements SingleDAOService<DAO,T,ID>{

  protected DAO dao;

  public DAO getDao() {
    return dao;
  }

  public void setDao(DAO dao) {
    this.dao = dao;
  }

  @Override
  public void saveNew(T t) {
    dao.save(t);
  }

  @Override
  public void update(T t) {
    dao.update(t);
  }

  @Override
  public void deleteById(ID id) {
    T t=dao.load(id);
    if (t!=null)
      dao.delete(t);
  }

  @Override
  public T loadById(ID id) {
    return dao.load(id);
  }

  @Override
  public Boolean isRecordExist(List<QueryCondition> queryConditions) {
    return dao.count(queryConditions)>0?true:false;
  }

  @Override
  public Boolean isRecordExist(QueryCondition queryCondition) {
    List<QueryCondition> queryConditions=new ArrayList<QueryCondition>(1);
    queryConditions.add(queryCondition);
    return isRecordExist(queryConditions);
  }

  @Override
  public List<T> queryPagedResult(List<QueryCondition> queryConditions, List<OrderCondition> orderConditions, int startRow, int pageSize) {
    return dao.queryPagedResult(queryConditions,orderConditions,startRow,pageSize);
  }

  @Override
  public Long count(List<QueryCondition> queryConditions) {
    return dao.count(queryConditions);
  }
}
