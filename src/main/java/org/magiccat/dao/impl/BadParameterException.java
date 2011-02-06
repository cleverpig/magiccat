package org.magiccat.dao.impl;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-6
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */
public class BadParameterException extends RuntimeException{
  public BadParameterException(String s) {
    super(s);
  }
}
