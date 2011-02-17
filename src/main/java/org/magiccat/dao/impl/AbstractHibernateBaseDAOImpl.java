package org.magiccat.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.magiccat.dao.AbstractHibernateBaseDAO;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.util.GenericTypeTool;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public class AbstractHibernateBaseDAOImpl<T,ID extends Serializable>
    extends HibernateDaoSupport implements AbstractHibernateBaseDAO<T,ID> {
  private Class<T> entityClazz;
  protected static final String ENTITY_ALIAS="e";
  private Log log= LogFactory.getLog(AbstractHibernateBaseDAOImpl.class);

  public AbstractHibernateBaseDAOImpl() {
    entityClazz= GenericTypeTool.getGenericTypeFromSuperclass(getClass());
    log.debug("entityClazz:"+entityClazz);
  }

  @Override
  public ID save(T entity){
    return (ID) getHibernateTemplate().save(entity);
  }

  @Override
  public void update(T entity){
    getHibernateTemplate().merge(entity);
//    getHibernateTemplate().update(entity);
  }

  @Override
  public void delete(T entity){
    getHibernateTemplate().delete(entity);
  }

  protected void appendOrderSQL(
      StringBuffer hsql,
      final String entityAlias,
      final String orderField,
      final boolean orderByAsc){
    if (StringUtils.isEmpty(orderField)==false){
      if (hsql.indexOf("ORDER BY")<0)
          hsql.append(" ORDER BY ");
      else
          hsql.append(",");

      hsql.append(entityAlias+"."+orderField);
      if (orderByAsc==false){
        hsql.append(" DESC");
      }
    }
  }
  @Override
  public List<T> list(String orderField, boolean orderByAsc){
    String entityName=entityClazz.getName();
    StringBuffer hsql=new StringBuffer();
    hsql.append( "FROM "+entityName);
    appendOrderSQL(hsql,ENTITY_ALIAS,orderField,orderByAsc);
    return getHibernateTemplate().find(hsql.toString());
  }

  protected void appendWhereSQL(
      StringBuffer hsql,
      final String entityAlias,
      final String queryField,
      final Object queryValue,
      final String operator,
      final String prefixRelation){
    if(StringUtils.isEmpty(queryField)==false && queryValue!=null){
      if (hsql.indexOf("WHERE")<0)
        hsql.append(" WHERE ");
      else
        hsql.append(prefixRelation);
      hsql.append("(").append(entityAlias).append(".").append(queryField).append(operator).append("?)");
    }
  }
  protected void appendWhereSQL(
      StringBuffer hsql,
      final String entityAlias,
      final String queryField,
      final Object queryValue){
    appendWhereSQL(hsql, entityAlias, queryField, queryValue, QueryCondition.EQ_OP, QueryCondition.AND_RELATION);
  }
  @Override
  public List<T> query(
      String queryField, Object queryValue,
      String orderField, boolean orderByAsc){
    String entityName=entityClazz.getName();
    StringBuffer hsql=new StringBuffer();
    hsql.append( "FROM "+entityName+" "+ENTITY_ALIAS);
    appendWhereSQL(
        hsql, ENTITY_ALIAS, queryField, queryValue,
        QueryCondition.EQ_OP,QueryCondition.AND_RELATION);
    appendOrderSQL(hsql,ENTITY_ALIAS,orderField,orderByAsc);
    if (queryValue!=null)
      return getHibernateTemplate().find(hsql.toString(),queryValue);
    else
      return getHibernateTemplate().find(hsql.toString());
  }

  @Override
  public T load(ID id) {
    log.info("load...entityClazz:"+entityClazz.getName());
    return getHibernateTemplate().load(entityClazz,id);
  }

  @Override
  public Long count(String queryField, Object queryValue) {
    StringBuffer hsql=new StringBuffer();
    hsql.append("SELECT COUNT(*) FROM "+entityClazz.getName()+" "+ENTITY_ALIAS);
    appendWhereSQL(
        hsql,ENTITY_ALIAS,queryField,queryValue,
        QueryCondition.EQ_OP,QueryCondition.AND_RELATION);
    List<Long> count=null;
    if (queryValue!=null)
      count= getHibernateTemplate().find(hsql.toString(),queryValue);
    else
      count= getHibernateTemplate().find(hsql.toString());
    return count.get(0);
  }

  @Override
  public Long count(List<QueryCondition> queryConditions) {
    StringBuffer hsql=new StringBuffer();
    hsql.append("SELECT COUNT(*) FROM "+entityClazz.getName()+" "+ENTITY_ALIAS);
    appendWhereSQL(hsql,queryConditions);
    List<Long> count=null;
    count=getHibernateTemplate().find(hsql.toString(),collectQueryParamValues(queryConditions));
    return count.get(0);
  }

  @Override
  public List<T> queryPagedResult(
      final String queryField,
      final Object queryValue,
      final String orderField,
      final boolean orderByAsc,
      final int startRow,
      final int pageSize) {
    final StringBuffer hsql=new StringBuffer();
    hsql.append( "FROM "+entityClazz.getName()+" "+ENTITY_ALIAS);
    appendWhereSQL(
        hsql, ENTITY_ALIAS, queryField, queryValue,
        QueryCondition.EQ_OP,QueryCondition.AND_RELATION);
    appendOrderSQL(hsql,ENTITY_ALIAS,orderField,orderByAsc);

    List<T> resultset= getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
      @Override
      public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
        session.createCriteria(hsql.toString());
        Query query=session.createQuery(hsql.toString());
        query.setParameter(0,queryValue);
        query.setFirstResult(startRow);
        query.setMaxResults(pageSize);
        return query.list();
      }
    });
    if(resultset!=null && resultset.size()>0){
      return resultset;
    }
    else
      return null;
  }

  @Override
  public List<T> queryPagedResult(
      final List<QueryCondition> queryConditions,
      final List<OrderCondition> orderConditions,
      final int startRow,
      final int pageSize) {
    final StringBuffer hsql=new StringBuffer();
    hsql.append( "FROM "+entityClazz.getName()+" "+ENTITY_ALIAS);
    appendWhereSQL(hsql,queryConditions);
    appendOrderSQL(hsql,orderConditions);
    final Object[] queryParamValues=collectQueryParamValues(queryConditions);

    List<T> resultset=getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
      @Override
      public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
        session.createCriteria(hsql.toString());
        Query query=session.createQuery(hsql.toString());
        int paramIndex=0;
        for(Object paramValue:queryParamValues){
          query.setParameter(paramIndex,paramValue);
          paramIndex++;
        }

        query.setFirstResult(startRow);
        query.setMaxResults(pageSize);
        return query.list();
      }
    });
    if(resultset!=null && resultset.size()>0){
      return resultset;
    }
    else
      return null;
  }

  @Override
  public List<T> queryPagedResult(List<QueryCondition> queryConditions, String orderField, boolean orderByAsc, int startRow, int pageSize) {
    List<OrderCondition> orderConditions=new ArrayList<OrderCondition>(1);
    orderConditions.add(new OrderCondition(orderField,orderByAsc));
    return queryPagedResult(queryConditions,orderConditions,startRow,pageSize);
  }

  @Override
  public List<T> queryPagedResult(List<QueryCondition> queryConditions, OrderCondition orderCondition, int startRow, int pageSize) {
    List<OrderCondition> orderConditions=new ArrayList<OrderCondition>(1);
    orderConditions.add(orderCondition);
    return queryPagedResult(queryConditions,orderConditions,startRow,pageSize);
  }

  @Override
  public T loadAndInitializeIt(ID id) {
    Session session=getHibernateTemplate().getSessionFactory().openSession();
    T entity= (T) session.load(entityClazz,id);
    getHibernateTemplate().initialize(entity);
    session.close();
    return entity;
  }


  protected void appendWhereSQL(
      StringBuffer hsql,
      final List<QueryCondition> queryConditions){
    for(QueryCondition queryCondition:queryConditions){
      appendWhereSQL(
          hsql,ENTITY_ALIAS,queryCondition.getName(),queryCondition.getValue(),
          queryCondition.getOperator(),queryCondition.getPrefixRelation());
    }
  }

  protected void appendOrderSQL(
      StringBuffer hsql,
      final List<OrderCondition> orderConditions){
    for(OrderCondition orderCondition:orderConditions){
      appendOrderSQL(hsql,ENTITY_ALIAS,orderCondition.getName(),orderCondition.getAsc());
    }
  }

  protected Object[] collectQueryParamValues(
      final List<QueryCondition> queryConditions){
    Object[] params=new Object[(queryConditions.size())];
    int index=0;
    for(QueryCondition queryCondition:queryConditions){
      params[index]=queryCondition.getValue();
      index++;
    }
    return params;
  }
  @Override
  public List<T> query(List<QueryCondition> queryConditions, List<OrderCondition> orderConditions) {
    StringBuffer hsql=new StringBuffer();
    hsql.append( "FROM "+entityClazz.getName()+" "+ENTITY_ALIAS);
    appendWhereSQL(hsql,queryConditions);
    appendOrderSQL(hsql,orderConditions);
    return getHibernateTemplate().find(hsql.toString(),collectQueryParamValues(queryConditions));
  }

  protected Class<T> getEntityClazz() {
    return entityClazz;
  }
}
