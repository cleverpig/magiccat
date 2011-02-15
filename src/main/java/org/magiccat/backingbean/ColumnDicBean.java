package org.magiccat.backingbean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.dic.ColumnDic;
import org.magiccat.service.ColumnDicService;
import org.magiccat.util.BeanHelper;
import org.magiccat.util.BooleanSelectItems;
import org.magiccat.util.MessageHelper;
import org.magiccat.util.QueryHelper;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-7
 * Time: 下午3:17
 * To change this template use File | Settings | File Templates.
 */
public class ColumnDicBean extends SortableBean implements QueryableBean{
  private ColumnDicService service;
  private SingleDAOServiceDataModel listData;
  private int pageSize;
  private Log log= LogFactory.getLog(ColumnDicBean.class);
  private ColumnDic dic;
  private Integer id;
  private Boolean showEdit;
  private Boolean showList;
  private Boolean showDetail;
  private String queryEntryVal;
  private List<SelectItem> enableOptions;
  private final String FORM_ID="form";

  public ColumnDicBean() {
    super("entryId");
    showEdit=false;
    showList=true;
    showDetail=false;
    enableOptions=BooleanSelectItems.OPTIONS;
  }

  public SingleDAOServiceDataModel getListData() {
    return listData;
  }

  public void setListData(SingleDAOServiceDataModel listData) {
    this.listData = listData;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public ColumnDicService getService() {
    return service;
  }

  public void setService(ColumnDicService service) {
    this.service = service;
  }

  public ColumnDic getDic() {
    return dic;
  }

  public void setDic(ColumnDic dic) {
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

  public String getQueryEntryVal() {
    return queryEntryVal;
  }

  public void setQueryEntryVal(String queryEntryVal) {
    this.queryEntryVal = queryEntryVal;
  }

  @Override
  public List<QueryCondition> constructQueryConditions(){
    List<QueryCondition> queryConditions = new ArrayList<QueryCondition>();
    QueryCondition queryEntryValCondition= QueryHelper.constructLikeCondition("entryVal", queryEntryVal, QueryCondition.AND_RELATION);
    log.debug("remarkListDataIsDirty...queryEntryVal="+queryEntryVal);
    if (queryEntryValCondition!=null){
      queryConditions.add(queryEntryValCondition);
      log.debug("remarkListDataIsDirty...queryEntryValCondition="+queryEntryValCondition.toString());
    }
    return queryConditions;
  }

  @Override
  public List<OrderCondition> constructOrderConditions(){
    List<OrderCondition> orderConditions=new ArrayList<OrderCondition>(1);
    orderConditions.add(new OrderCondition(getSortColumnName(),isSortAscending()));
    return orderConditions;
  }

  @Override
  public void notifyDataModelChange(){
    listData.setQueryConditions(constructQueryConditions());
    listData.setOrderConditions(constructOrderConditions());
    listData.setDirtyData(true);
  }

  @PostConstruct
  public void loadDicData(){
    log.debug("loadDicData...");
    if (listData==null){
      listData=new SingleDAOServiceDataModel(
          service,pageSize,
          constructQueryConditions(),
          constructOrderConditions());
    }
  }

  private boolean loadDicById(ActionEvent event,String paramName){
    UIParameter param= (UIParameter) event.getComponent().findComponent(paramName);
    dic= service.loadById((Integer) param.getValue());
    if (dic!=null){
      return true;
    }
    else{
      BeanHelper.addMessageToContext(
            "数据不存在","试图取出id值为\"+param.getValue()+\"的数据时没有找到数据",
            FacesMessage.SEVERITY_ERROR,FORM_ID
        );
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
    //update
    if (dic!=null && dic.getId()!=null && dic.getId()>0){
      ColumnDic oldDic= service.loadById(dic.getId());
      if (oldDic.getEntryId().equals(dic.getEntryId())==false){//user had modified entryId!
        if (service.isRecordExist(dic.getEntryId())){//this modified entryId has been existed!
          BeanHelper.addMessageToContext(FORM_ID,
              MessageHelper.constructFacesMessageFromBundle(
                  MessageHelper.VALIDATE_RESOURCE_BUNDLE,MessageHelper.MESSAGE_TYPE.DATA_EXSIT
              )
          );
          return;
        }
        else{//user's entryId didn't exist!
          service.update(dic);
          returnToList();
          notifyDataModelChange();
        }
      }
      else{//user hadn't modified entryId
        service.update(dic);
        returnToList();
        notifyDataModelChange();
      }

    }
    else{
      //saveNew
      if (dic!=null){
        if (service.isRecordExist(dic.getEntryId())==false){
          service.saveNew(dic);
          returnToList();
          notifyDataModelChange();
        }
        else{
          BeanHelper.addMessageToContext(FORM_ID,
              MessageHelper.constructFacesMessageFromBundle(
                  MessageHelper.VALIDATE_RESOURCE_BUNDLE,MessageHelper.MESSAGE_TYPE.DATA_EXSIT
              )
          );
        }
      }
      else{
        BeanHelper.addMessageToContext(FORM_ID,
            MessageHelper.constructFacesMessageFromBundle(
                MessageHelper.VALIDATE_RESOURCE_BUNDLE,MessageHelper.MESSAGE_TYPE.DATA_IS_NULL
            ));
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
    dic=new ColumnDic();
    showEdit=true;
    showDetail=false;
    showList=false;
  }

  public void delActionHandler(ActionEvent event){
    UIParameter param= (UIParameter) event.getComponent().findComponent("id");
    service.deleteById((Integer) param.getValue());
    notifyDataModelChange();
  }

  public void queryActionHandler(ActionEvent event){
    notifyDataModelChange();
  }
  @Override
  public boolean isDefaultAscending(String sortColumn) {
    return true;
  }

}

