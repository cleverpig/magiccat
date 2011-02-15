package org.magiccat.service;

import org.magiccat.dao.ColumnDicDAO;
import org.magiccat.domain.dic.ColumnDic;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-15
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
public interface ColumnDicService extends SingleDAOService<ColumnDicDAO,ColumnDic,Integer> {
  public Boolean isRecordExist(String entryId);
}
