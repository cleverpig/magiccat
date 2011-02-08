package org.magiccat.dao;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-8
 * Time: 下午12:15
 * To change this template use File | Settings | File Templates.
 */
public class OrderCondition {
  private String name;
  private Boolean isAsc;

  public OrderCondition(String name, Boolean asc) {
    this.name = name;
    isAsc = asc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getAsc() {
    return isAsc;
  }

  public void setAsc(Boolean asc) {
    isAsc = asc;
  }
}
