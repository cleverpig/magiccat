package org.magiccat.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-12
 * Time: 上午10:49
 * To change this template use File | Settings | File Templates.
 */
public class BeanHelper {
  public static void addMessageToContext(final String clientId,final FacesMessage facesMessage){
    FacesContext context=FacesContext.getCurrentInstance();
    context.addMessage(clientId,facesMessage);
  }
  public static void addMessageToContext(
      final String msgSummary,final String msgDetail,
      final FacesMessage.Severity severity,final String clientId ){
    FacesMessage message=new FacesMessage();
    message.setSeverity(severity);
    message.setSummary(msgSummary);
    message.setDetail(msgDetail);
    addMessageToContext(clientId,message);
  }
}
