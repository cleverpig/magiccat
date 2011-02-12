package org.magiccat.backingbean;

import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-12
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public interface QueryableBean{

  public List<QueryCondition> constructQueryConditions();
  public List<OrderCondition> constructOrderConditions();

}
