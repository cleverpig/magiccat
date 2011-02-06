package org.magiccat.dao;

import org.magiccat.domain.Dic;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-6
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public interface DicDAO extends AbstractHibernateBaseDAO<Dic,Integer>{
  Dic loadByCatTypesAndEntryId(String catTypes, String entryId) throws Exception;

  List<Dic> listByCatTypes(String catTypes) throws Exception;
}
