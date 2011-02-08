package org.magiccat.service.impl;

import org.magiccat.dao.DicDAO;
import org.magiccat.domain.Dic;
import org.magiccat.service.DicService;

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
  public List<Dic> queryPagedDics(String catType,String sortField,Boolean sortAscending,int startRow,int pageSize) {
    return dicDAO.queryPagedResult("catTypes",catType,sortField,sortAscending,startRow,pageSize);
  }
}
