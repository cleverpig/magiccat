package org.magiccat.dao;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-8
 * Time: 下午12:05
 * To change this template use File | Settings | File Templates.
 */
public class QueryCondition {
  private String name;
  private Object value;
  private String operator;
  private String prefixRelation;

  public static final String EQ_OP=" = ";
  public static final String GT_OP=" > ";
  public static final String LT_OP=" < ";
  public static final String GE_OP=" >= ";
  public static final String LE_OP=" <=";
  public static final String LIKE_OP=" like ";
  public static final String AND_RELATION=" AND ";
  public static final String OR_RELATION=" OR ";

  public QueryCondition(
      final String name,
      final Object value,
      final String operator,
      final String prefixRelation) {
    this.name = name;
    this.value = value;
    this.operator = operator;
    this.prefixRelation = prefixRelation;
    if (prefixRelation==null)
      this.prefixRelation=AND_RELATION;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getPrefixRelation() {
    return prefixRelation;
  }

  public void setPrefixRelation(String prefixRelation) {
    this.prefixRelation = prefixRelation;
  }
}
