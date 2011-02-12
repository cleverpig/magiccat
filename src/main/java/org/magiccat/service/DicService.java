package org.magiccat.service;

import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.Dic;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-6
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public interface DicService extends PaginationService<Dic>{
  List<Dic> queryDicsByCatType(String catType) throws Exception;

  Dic loadDic(String catType, String entryId) throws Exception;

  Dic loadDic(Integer id);

  void saveNewDic(Dic dic);

  void updateDic(Dic dic);

  void deleteDic(Integer id);

  Long count();

  List<Dic> queryPagedResult(
      String catType, String queryEntryVal,
      String sortField, Boolean sortAscending,
      int startRow, int pageSize);

  List<Dic> queryPagedResult(
      List<QueryCondition> queryConditions,
      OrderCondition orderCondition,
      int startRow, int pageSize);

  Boolean isRecordExist(String catType,String entryId);
}

