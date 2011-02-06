package org.magiccat.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.magiccat.dao.DicDAO;
import org.magiccat.domain.Dic;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-6
 * Time: 下午3:16
 * To change this template use File | Settings | File Templates.
 */
public class DicDAOImpl extends AbstractHibernateBaseDAOImpl<Dic,Integer> implements DicDAO {
  private Log log= LogFactory.getLog(DicDAOImpl.class);

  @Override
  public Dic loadByCatTypesAndEntryId(String catTypes, String entryId) throws Exception{
    StringBuffer hsql=new StringBuffer();
    hsql.append( "FROM "+getEntityClazz().getName()+" "+ENTITY_ALIAS);
    appendWhereSQL(hsql, ENTITY_ALIAS, "catTypes", catTypes);
    appendWhereSQL(hsql, ENTITY_ALIAS, "entryId", entryId);
    appendWhereSQL(hsql, ENTITY_ALIAS, "isEnabled", true);
    if (StringUtils.isEmpty(catTypes)==false && StringUtils.isEmpty(entryId)==false){
      List dics=getHibernateTemplate().find(hsql.toString(),catTypes,entryId,true);
      if (dics!=null && dics.size()==1){
        return (Dic) dics.get(0);
      }
      else if(dics==null || dics.size()==0){
        throw new EntityNotFoundException("we couldn't found entity from HSQL:"+hsql);
      }
      else{
        throw new NonUniqueResultException("we couldn't found only one entity from HSQL:"+hsql);
      }
    }
    else{
      throw new BadParameterException("you give a bad parameter on query condition");
    }
  }

  @Override
  public List<Dic> listByCatTypes(String catTypes){
    StringBuffer hsql=new StringBuffer();
    hsql.append("FROM " + getEntityClazz().getName() + " " + ENTITY_ALIAS);
    appendWhereSQL(hsql, ENTITY_ALIAS, "catTypes", catTypes);
    appendWhereSQL(hsql, ENTITY_ALIAS, "isEnabled", true);
    appendOrderSQL(hsql, ENTITY_ALIAS, "entryOrder", true);
    if (StringUtils.isEmpty(catTypes)==false){
      return getHibernateTemplate().find(hsql.toString(),catTypes,true);
    }
    else{
      throw new BadParameterException("you give a bad parameter on query condition");
    }
  }
}
