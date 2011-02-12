package org.magiccat.backingbean;

import com.icefaces.model.datamodel.PaginationDataModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.Dic;
import org.magiccat.service.DicService;
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
public class DicBean extends SortableBean implements QueryableBean{
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
  private String queryEntryVal;
  private List<SelectItem> enableOptions;
  private final String FORM_ID="form";

  public DicBean() {
    super("entryId");
    showEdit=false;
    showList=true;
    showDetail=false;
    enableOptions=BooleanSelectItems.OPTIONS;
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

  public String getQueryEntryVal() {
    return queryEntryVal;
  }

  public void setQueryEntryVal(String queryEntryVal) {
    this.queryEntryVal = queryEntryVal;
  }

  @Override
  public List<QueryCondition> constructQueryConditions(){
    List<QueryCondition> queryConditions = new ArrayList<QueryCondition>();
    queryConditions.add(new QueryCondition("catTypes",catType,QueryCondition.EQ_OP,QueryCondition.AND_RELATION));
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
      listData=new DicDataModel(
          dicService,pageSize,
          constructQueryConditions(),
          constructOrderConditions());
    }
  }

  private boolean loadDicById(ActionEvent event,String paramName){
    UIParameter param= (UIParameter) event.getComponent().findComponent(paramName);
    dic=dicService.loadDic((Integer) param.getValue());
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
      Dic oldDic=dicService.loadDic(dic.getId());
      if (oldDic.getEntryId().equals(dic.getEntryId())==false){//user had modified entryId!
        if (dicService.isRecordExist(catType,dic.getEntryId())){//this modified entryId has been existed!
          BeanHelper.addMessageToContext(FORM_ID,
              MessageHelper.constructFacesMessageFromBundle(
                  MessageHelper.VALIDATE_RESOURCE_BUNDLE,MessageHelper.MESSAGE_TYPE.DATA_EXSIT
              )
          );
          return;
        }
        else{//user's entryId didn't exist!
          dicService.updateDic(dic);
          returnToList();
          notifyDataModelChange();
        }
      }
      else{//user hadn't modified entryId
        dicService.updateDic(dic);
        returnToList();
        notifyDataModelChange();
      }

    }
    else{
      //save
      if (dic!=null){
        if (dicService.isRecordExist(catType,dic.getEntryId())==false){
          dicService.saveNewDic(dic);
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
    dic=new Dic();
    showEdit=true;
    showDetail=false;
    showList=false;
  }

  public void delActionHandler(ActionEvent event){
    UIParameter param= (UIParameter) event.getComponent().findComponent("id");
    dicService.deleteDic((Integer)param.getValue());
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
class DicDataModel extends PaginationDataModel<Dic> {

  public DicDataModel(
      DicService dicService, int rowsPerPage,
      List<QueryCondition> queryConditions,
      List<OrderCondition> orderConditions) {
    super(dicService,rowsPerPage,queryConditions,orderConditions);
  }
}
