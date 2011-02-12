package org.magiccat.util;

import org.apache.commons.lang.StringUtils;
import org.magiccat.dao.QueryCondition;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-11
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
public class QueryHelper {
  public static final QueryCondition constructLikeCondition(
      final String fieldName,final String fieldVal,final String queryConditionRelation){

    if (StringUtils.isNotEmpty(fieldVal)){
      StringBuffer sb=new StringBuffer(fieldVal);
      if (fieldVal.startsWith("%")==false){
        sb.insert(0, "%");
      }
      if (fieldVal.endsWith("%")==false){
        sb.append("%");
      }
      return new QueryCondition(
          fieldName,sb.toString(),
          QueryCondition.LIKE_OP,queryConditionRelation);

    }
    else{
      return null;
    }

  }
}
