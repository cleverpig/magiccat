package org.magiccat.service.impl;

import org.magiccat.dao.DicDAO;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.Dic;
import org.magiccat.service.DicService;
import org.magiccat.util.QueryHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-6
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
public class DicServiceImpl implements DicService {
  private DicDAO dicDAO;

  public DicDAO getDicDAO() {
    return dicDAO;
  }

  public void setDicDAO(DicDAO dicDAO) {
    this.dicDAO = dicDAO;
  }

  @Override
  public List<Dic> queryDicsByCatType(String catType) throws Exception {
    return dicDAO.listByCatTypes(catType);
  }

  @Override
  public Dic loadDic(String catType, String entryId) throws Exception {
    return dicDAO.loadByCatTypesAndEntryId(catType,entryId);
  }

  @Override
  public Dic loadDic(Integer id) {
    return dicDAO.load(id);
  }

  @Override
  public void saveNewDic(Dic dic){
    dicDAO.save(dic);
  }

  @Override
  public void updateDic(Dic dic){
    dicDAO.update(dic);
  }

  @Override
  public void deleteDic(Integer id){
    Dic dic= dicDAO.load(id);
    if (dic!=null)
      dicDAO.delete(dic);
  }

  @Override
  public Long count() {
    return dicDAO.count(null,null);
  }

  @Override
  public Long count(List<QueryCondition> queryConditions) {
    return dicDAO.count(queryConditions);
  }

  @Override
  public List<Dic> queryPagedResult(
      String catType, String queryEntryVal,
      String sortField, Boolean sortAscending,
      int startRow, int pageSize) {
    List<QueryCondition> queryConditions=new ArrayList<QueryCondition>();
    queryConditions.add(new QueryCondition("catTypes",catType,QueryCondition.EQ_OP,QueryCondition.AND_RELATION));
    QueryCondition queryEntryValCondition= QueryHelper.constructLikeCondition("entryVal", queryEntryVal, QueryCondition.AND_RELATION);
    if (queryEntryValCondition!=null)
      queryConditions.add(queryEntryValCondition);
    List<OrderCondition> orderConditions=new ArrayList<OrderCondition>();
    orderConditions.add(new OrderCondition(sortField,sortAscending));

    return dicDAO.queryPagedResult(queryConditions,orderConditions,startRow,pageSize);
  }

  @Override
  public List<Dic> queryPagedResult(List<QueryCondition> queryConditions, List<OrderCondition> orderConditions, int startRow, int pageSize) {
    return dicDAO.queryPagedResult(queryConditions,orderConditions,startRow,pageSize);
  }

  @Override
  public List<Dic> queryPagedResult(List<QueryCondition> queryConditions, OrderCondition orderCondition, int startRow, int pageSize) {
    return dicDAO.queryPagedResult(queryConditions,orderCondition,startRow,pageSize);
  }

  @Override
  public Boolean isRecordExist(String catType, String entryId) {
    List<QueryCondition> queryConditions=new ArrayList<QueryCondition>(2);
    queryConditions.add(new QueryCondition("catTypes",catType,QueryCondition.EQ_OP,QueryCondition.AND_RELATION));
    queryConditions.add(new QueryCondition("entryId",entryId,QueryCondition.EQ_OP,QueryCondition.AND_RELATION));
    return dicDAO.count(queryConditions)>0;
  }
}
