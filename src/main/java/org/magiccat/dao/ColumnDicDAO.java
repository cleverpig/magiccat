package org.magiccat.dao;

import org.magiccat.domain.dic.ColumnDic;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-15
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
public interface ColumnDicDAO extends AbstractHibernateBaseDAO<ColumnDic,Integer> {
  public ColumnDic loadByEntryId(String entryId);
}
