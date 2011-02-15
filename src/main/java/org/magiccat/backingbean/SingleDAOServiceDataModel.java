package org.magiccat.backingbean;

import com.icefaces.model.datamodel.PaginationDataModel;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.service.SingleDAOService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-15
 * Time: 下午4:46
 * To change this template use File | Settings | File Templates.
 */
public class SingleDAOServiceDataModel<T> extends PaginationDataModel<T> {

  public SingleDAOServiceDataModel(
      SingleDAOService singleDAOService, int rowsPerPage,
      List<QueryCondition> queryConditions,
      List<OrderCondition> orderConditions) {
    super(singleDAOService,rowsPerPage,queryConditions,orderConditions);
  }
}
