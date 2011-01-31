package org.magiccat.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.magiccat.dao.AbstractHibernateBaseDAO;
import org.magiccat.util.GenericTypeTool;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
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
  private static final String ENTITY_ALIAS="e";
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
    getHibernateTemplate().update(entity);
  }

  @Override
  public void delete(T entity){
    getHibernateTemplate().delete(entity);
  }

  private void appendOrderSQL(
      StringBuffer hsql,
      final String orderField,
      final boolean orderByAsc){
    if (StringUtils.isEmpty(orderField)==false){
      hsql.append(" ORDER BY "+orderField);
      if (orderByAsc==false){
        hsql.append(" DESC");
      }
    }
  }
  @Override
  public List<T> list(String orderField, boolean orderByAsc){
    String entityName=entityClazz.getSimpleName();
    StringBuffer hsql=new StringBuffer();
    hsql.append( "FROM "+entityName);
    appendOrderSQL(hsql,orderField,orderByAsc);
    return getHibernateTemplate().find(hsql.toString());
  }

  private void appendWhereSQL(
      StringBuffer hsql,
      final String entityAlias,
      final String queryField,
      final Object queryValue){
    if(StringUtils.isEmpty(queryField)==false && queryValue!=null){
      hsql.append(" WHERE ").append(entityAlias).append(".").append(queryField).append("=?");
    }
  }
  @Override
  public List<T> query(
      String queryField, Object queryValue,
      String orderField, boolean orderByAsc){
    String entityName=entityClazz.getSimpleName();
    StringBuffer hsql=new StringBuffer();
    hsql.append( "FROM "+entityName+" "+ENTITY_ALIAS);
    appendWhereSQL(hsql, ENTITY_ALIAS, queryField, queryValue);
    appendOrderSQL(hsql,orderField,orderByAsc);
    if (queryValue!=null)
      return getHibernateTemplate().find(hsql.toString(),queryValue);
    else
      return getHibernateTemplate().find(hsql.toString());
  }

  @Override
  public T load(ID id) {
    return getHibernateTemplate().load(entityClazz,id);
  }


}
