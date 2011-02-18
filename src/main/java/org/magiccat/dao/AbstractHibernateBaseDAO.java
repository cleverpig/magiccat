package org.magiccat.dao;

import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
public interface AbstractHibernateBaseDAO<T,ID extends Serializable> {

  ID save(T entity);

  void update(T entity);

  void delete(T entity);

  void deleteById(ID id);

  List<T> list(String orderField, boolean orderByAsc);

  List<T> query(
      String queryField, Object queryValue,
      String orderField, boolean orderByAsc);

  List<T> query(
      List<QueryCondition> queryConditions,
      List<OrderCondition> orderConditions);

  T load(ID id);

  Long count(String queryField, Object queryValue);

  Long count(List<QueryCondition> queryConditions);

  List<T> queryPagedResult(
      String queryField, Object queryValue,
      String orderField, boolean orderByAsc,
      int startRow,int pageSize);

  List<T> queryPagedResult(
      List<QueryCondition> queryConditions,
      List<OrderCondition> orderConditions,
      int startRow,int pageSize);

  List<T> queryPagedResult(
      List<QueryCondition> queryConditions,
      String orderField, boolean orderByAsc,
      int startRow,int pageSize);

  List<T> queryPagedResult(
      List<QueryCondition> queryConditions,
      OrderCondition orderCondition,
      int startRow,int pageSize);

  SessionFactory getSessionFactory();

  /**
   * load object and then call hibernate to initialize this object to avoid lazy load problem.
   * sometime,this function was used for DAO test,since spring can resolve this problem with opensessionView..
   * @param id
   * @return
   */
  T loadAndInitializeIt(ID id);

  T loadByExample(T example);
}
