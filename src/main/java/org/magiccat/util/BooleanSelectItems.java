package org.magiccat.util;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-10
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */
public class BooleanSelectItems {
  public static final SelectItem TRUE_SELECT_ITEM=new SelectItem(Boolean.TRUE,"是");
  public static final SelectItem FALSE_SELECT_ITEM=new SelectItem(Boolean.FALSE,"否");

  public static List<SelectItem> OPTIONS =new ArrayList<SelectItem>();
  static {
    OPTIONS.add(TRUE_SELECT_ITEM);
    OPTIONS.add(FALSE_SELECT_ITEM);
  }

}
