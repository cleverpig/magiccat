package org.magiccat.backingbean;

import org.magiccat.domain.Dic;
import org.magiccat.service.DicService;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-7
 * Time: 下午3:17
 * To change this template use File | Settings | File Templates.
 */
public class DicBean {
  private List<Dic> dics;
  private DicService dicService;
  private String catType;

  public List<Dic> getDics() {
    return dics;
  }

  public void setDics(List<Dic> dics) {
    this.dics = dics;
  }

  public String getCatType() {
    return catType;
  }

  public void setCatType(String catType) {
    this.catType = catType;
  }

  public DicService getDicService() {
    return dicService;
  }

  public void setDicService(DicService dicService) {
    this.dicService = dicService;
  }

  @PostConstruct
  public void loadDicData() throws Exception {
    dics=dicService.queryDicsByCatType(catType);
  }

  public void showActionHandler(ActionEvent event){

  }

  public void editActionHandler(ActionEvent event){

  }
}
