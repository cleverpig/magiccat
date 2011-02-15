package org.magiccat.service;

import org.magiccat.dao.QueryCondition;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-15
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public interface GenericDBOperationService<T,ID extends Serializable> {
  public void saveNew(T t);
  public void update(T t);
  public void deleteById(ID id);
  public T loadById(ID id);
  public Boolean isRecordExist(List<QueryCondition> queryConditions);
  public Boolean isRecordExist(QueryCondition queryCondition);
}
