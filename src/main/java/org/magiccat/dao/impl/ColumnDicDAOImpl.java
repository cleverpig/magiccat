package org.magiccat.dao.impl;

import org.magiccat.dao.ColumnDicDAO;
import org.magiccat.domain.dic.ColumnDic;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-15
 * Time: 下午4:23
 * To change this template use File | Settings | File Templates.
 */
public class ColumnDicDAOImpl extends AbstractHibernateBaseDAOImpl<ColumnDic,Integer>  implements ColumnDicDAO{

  @Override
  public ColumnDic loadByEntryId(String entryId) {
    List<ColumnDic> columnDics=query("entryId",entryId,null,true);
    if (columnDics!=null && columnDics.size()>0){
      return columnDics.get(0);
    }
    else
      return null;
  }
}
