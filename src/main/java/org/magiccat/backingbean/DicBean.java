package org.magiccat.backingbean;

import com.icefaces.model.datamodel.SortableDataModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class DicBean extends SortableDataModel {
//  private List<Dic> dics;
  private DicService dicService;
  private String catType;
  private DicDataModel listData;
  private int pageSize;
  private Log log= LogFactory.getLog(DicBean.class);

  public DicBean() {
    super("entryId");
  }

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
//    log.debug("getListData...");
    loadDicData();
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
  public void loadDicData(){
//    dics=dicService.queryDicsByCatType(catType);
    log.debug("loadDicData...");
    listData=new DicDataModel(
        catType,dicService,pageSize,
        getSortColumnName(),isSortAscending());
  }

  public void showActionHandler(ActionEvent event){

  }

  public void editActionHandler(ActionEvent event){

  }

  @Override
  public boolean isDefaultAscending(String sortColumn) {
    return true;
  }
}
