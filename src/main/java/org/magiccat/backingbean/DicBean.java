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
//  private List<Dic> dics;
  private DicService dicService;
  private String catType;
  private DicDataModel listData;
  private int pageSize;

//  public List<Dic> getDics() {
//    return dics;
//  }

//  public void setDics(List<Dic> dics) {
//    this.dics = dics;
//  }

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

  public DicDataModel getListData() {
    return listData;
  }

  public void setListData(DicDataModel listData) {
    this.listData = listData;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  @PostConstruct
  public void loadDicData() throws Exception {
//    dics=dicService.queryDicsByCatType(catType);
    listData=new DicDataModel(catType,dicService,pageSize);
  }

  public void showActionHandler(ActionEvent event){

  }

  public void editActionHandler(ActionEvent event){

  }
}
