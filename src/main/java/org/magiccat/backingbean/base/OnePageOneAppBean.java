package org.magiccat.backingbean.base;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-17
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class OnePageOneAppBean extends SortableBean  implements QueryableBean{
  private Boolean showEdit;
  private Boolean showList;
  private Boolean showDetail;
  private int pageSize;

  public OnePageOneAppBean(String defaultSortColumn) {
    super(defaultSortColumn);
    showEdit=false;
    showList=true;
    showDetail=false;
  }

  public abstract void loadData();

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

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public void switchToShowEditor(){
    showEdit=true;
    showList=false;
    showDetail=false;
  }

  public void switchToShowList(){
    showEdit=false;
    showList=true;
    showDetail=false;
  }

  public void switchToShowDetail(){
    showEdit=false;
    showList=false;
    showDetail=true;
  }
}
