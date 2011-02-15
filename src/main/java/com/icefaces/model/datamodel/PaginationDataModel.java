package com.icefaces.model.datamodel;

import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.service.PaginationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-8
 * Time: 上午10:43
 * To change this template use File | Settings | File Templates.
 */
public class PaginationDataModel<T> extends LazyDataModel {
  private PaginationService service;
  private int rowsPerPage;
  private List<QueryCondition> queryConditions;
  private List<OrderCondition> orderConditions;

  public PaginationDataModel(
      PaginationService service,
      int rowsPerPage,
      List<QueryCondition> queryConditions,
      List<OrderCondition> orderConditions) {
    this.service = service;
    this.rowsPerPage = rowsPerPage;
    this.queryConditions = queryConditions;
    this.orderConditions = orderConditions;
  }

  public PaginationDataModel(
      PaginationService service,
      int rowsPerPage,
      List<QueryCondition> queryConditions,
      OrderCondition orderCondition) {
    this.service = service;
    this.rowsPerPage = rowsPerPage;
    this.queryConditions = queryConditions;
    List<OrderCondition> orderConditions=new ArrayList<OrderCondition>(1);
    orderConditions.add(orderCondition);
    this.orderConditions = orderConditions;
  }

  @Override
  public int getRowsPerPage() {
    return rowsPerPage;
  }

  @Override
  public int countRows() {
    return service.count(queryConditions).intValue();
  }

  public PaginationService getService() {
    return service;
  }

  public List<QueryCondition> getQueryConditions() {
    return queryConditions;
  }

  public void setQueryConditions(List<QueryCondition> queryConditions) {
    this.queryConditions = queryConditions;
  }

  public List<OrderCondition> getOrderConditions() {
    return orderConditions;
  }

  public void setOrderConditions(List<OrderCondition> orderConditions) {
    this.orderConditions = orderConditions;
  }

  @Override
  public List<T> findRows(int startRow, int finishRow) {
    return service.queryPagedResult(
        queryConditions, orderConditions,
        startRow, startRow + getRowsPerPage());
  }
}
