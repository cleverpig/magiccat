package org.magiccat.service;

import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.Dic;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-12
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public interface PaginationService<T> {
  List<T> queryPagedResult(
      List<QueryCondition> queryConditions,
      List<OrderCondition> orderConditions,
      int startRow,int pageSize);

  Long count(List<QueryCondition> queryConditions);
}
