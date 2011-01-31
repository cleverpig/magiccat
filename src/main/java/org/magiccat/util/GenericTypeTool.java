package org.magiccat.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
public class GenericTypeTool {
  public static Class getGenericTypeFromSuperclass(Class clazz){
    Type genericSuperClass=clazz.getGenericSuperclass();
    Type[] params=((ParameterizedType)genericSuperClass).getActualTypeArguments();
    return (Class) params[0];
  }
}
