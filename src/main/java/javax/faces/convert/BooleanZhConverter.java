package javax.faces.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-10
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
public class BooleanZhConverter extends BooleanConverter {
  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
    if (s.equalsIgnoreCase("true")){
      return Boolean.TRUE;
    }
    else if (s.equalsIgnoreCase("false")){
      return Boolean.FALSE;
    }
    else{
      return super.getAsObject(facesContext,uiComponent,s);
    }
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
    if (o instanceof Boolean){
      Boolean b=(Boolean)o;
      if (b){
        return "是";
      }
      else{
        return "否";
      }
    }
    else
      return super.getAsString(facesContext,uiComponent,o);
  }
}
