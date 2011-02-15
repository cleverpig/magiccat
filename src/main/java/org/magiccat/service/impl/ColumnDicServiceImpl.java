package org.magiccat.service.impl;

import org.magiccat.dao.ColumnDicDAO;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.dic.ColumnDic;
import org.magiccat.service.ColumnDicService;


/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-15
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
public class ColumnDicServiceImpl extends AbstractSingleDAOService<ColumnDicDAO,ColumnDic,Integer> implements ColumnDicService{
  @Override
  public Boolean isRecordExist(String entryId) {
    return isRecordExist(new QueryCondition("entryId",entryId,QueryCondition.EQ_OP,QueryCondition.AND_RELATION));
  }
}
