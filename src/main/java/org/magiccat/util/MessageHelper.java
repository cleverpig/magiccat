package org.magiccat.util;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-12
 * Time: 下午2:23
 * To change this template use File | Settings | File Templates.
 */
public class MessageHelper {
  public static ResourceBundle getResourceBundle(final String bundleName){
    FacesContext facesContext=FacesContext.getCurrentInstance();
    Locale locale = facesContext.getViewRoot().getLocale();
    return facesContext.getApplication().getResourceBundle(facesContext,bundleName);
  }

  public static String getResourceBundleValue(final String bundleName,final String key){
    ResourceBundle resourceBundle = getResourceBundle(bundleName);
    return resourceBundle.getString(key);
  }

  public static FacesMessage constructFacesMessageFromBundle(
      final String bundleName,final MESSAGE_TYPE messageType){
    ResourceBundle rb=getResourceBundle(bundleName);
    String msgSummaryKey=messageType.toString()+"_SUMMARY";
    String msgDetailKey=messageType.toString()+"_DETAIL";

    FacesMessage facesMessage=new FacesMessage();
    facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
    facesMessage.setSummary(rb.getString(msgSummaryKey));
    facesMessage.setDetail(rb.getString(msgDetailKey));

    return facesMessage;

  }
  public static enum MESSAGE_TYPE{
    DATA_EXSIT,DATA_IS_NULL
  };

  public static final String VALIDATE_RESOURCE_BUNDLE ="validate";
}
