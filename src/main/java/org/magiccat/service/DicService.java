package org.magiccat.service;

import org.magiccat.domain.Dic;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-6
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public interface DicService {
  List<Dic> queryDicsByCatType(String catType) throws Exception;

  Dic loadDic(String catType, String entryId) throws Exception;

  void saveNewDic(Dic dic);

  void updateDic(Dic dic);

  void deleteDic(Integer id);

  Long count();

  List<Dic> queryPagedDics(String catType,String sortField,Boolean sortAscending,int startRow,int pageSize);
}
