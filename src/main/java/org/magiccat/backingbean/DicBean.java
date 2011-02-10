package org.magiccat.backingbean;

import com.sun.facelets.FaceletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.magiccat.domain.Dic;
import org.magiccat.service.DicService;
import org.magiccat.util.BooleanSelectItems;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-7
 * Time: 下午3:17
 * To change this template use File | Settings | File Templates.
 */
public class DicBean extends SortableBean {
//  private List<Dic> dics;
  private DicService dicService;
  private String catType;
  private DicDataModel listData;
  private int pageSize;
  private Log log= LogFactory.getLog(DicBean.class);
  private Dic dic;
  private Integer id;
  private Boolean showEdit;
  private Boolean showList;
  private Boolean showDetail;

  private List<SelectItem> enableOptions;

  public DicBean() {
    super("entryId");
    showEdit=false;
    showList=true;
    showDetail=false;
    enableOptions=BooleanSelectItems.OPTIONS;
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

  public Dic getDic() {
    return dic;
  }

  public void setDic(Dic dic) {
    this.dic = dic;
  }

  public Boolean getShowEdit() {
    return showEdit;
  }

  public void setShowEdit(Boolean showEdit) {
    this.showEdit = showEdit;
  }

  public Boolean getShowList() {
    return showList;
  }

  public void setShowList(Boolean showList) {
    this.showList = showList;
  }

  public Boolean getShowDetail() {
    return showDetail;
  }

  public void setShowDetail(Boolean showDetail) {
    this.showDetail = showDetail;
  }

  public List<SelectItem> getEnableOptions() {
    return enableOptions;
  }

  public void setEnableOptions(List<SelectItem> enableOptions) {
    this.enableOptions = enableOptions;
  }

  @PostConstruct
  public void loadDicData(){
//    dics=dicService.queryDicsByCatType(catType);
    log.debug("loadDicData...");
    listData=new DicDataModel(
        catType,dicService,pageSize,
        getSortColumnName(),isSortAscending());
  }

  private boolean loadDicById(ActionEvent event,String paramName){
    UIParameter param= (UIParameter) event.getComponent().findComponent(paramName);
    dic=dicService.loadDic((Integer) param.getValue());
    if (dic!=null){
      return true;
    }
    else{
      FacesContext context=FacesContext.getCurrentInstance();
      FacesMessage message=new FacesMessage();
      message.setSeverity(FacesMessage.SEVERITY_ERROR);
      message.setSummary("数据不存在");
      message.setDetail("试图取出id值为"+param.getValue()+"的数据时没有找到数据");
      context.addMessage("form",message);
      return false;
    }
  }

  public void showActionHandler(ActionEvent event){
    if (loadDicById(event,"id")){
      showList=false;
      showEdit=false;
      showDetail=true;
    }
  }

  public void editActionHandler(ActionEvent event){
    if (loadDicById(event,"id")){
      showEdit=true;
      showList=false;
      showDetail=false;
    }
  }

  public void saveActionHandler(ActionEvent event){
    if (dic!=null && dic.getId()!=null && dic.getId()>0){
      dicService.updateDic(dic);
      returnToList();
    }
    else{
      if (dic!=null){
        dicService.saveNewDic(dic);
        returnToList();
      }
      else{
        FacesContext context=FacesContext.getCurrentInstance();
        FacesMessage message=new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        message.setSummary("数据无法保存");
        message.setDetail("提交的数据为空");
        context.addMessage("form",message);
      }
    }

  }

  private void returnToList(){
    dic=null;
    showEdit=false;
    showDetail=false;
    showList=true;
  }

  public void returnActionHandler(ActionEvent event){
    returnToList();
  }

  public void newActionHandler(ActionEvent event){
    dic=new Dic();
    showEdit=true;
    showDetail=false;
    showList=false;
  }

  public void delActionHandler(ActionEvent event){
    UIParameter param= (UIParameter) event.getComponent().findComponent("id");
    dicService.deleteDic((Integer)param.getValue());
  }

  @Override
  public boolean isDefaultAscending(String sortColumn) {
    return true;
  }
}
