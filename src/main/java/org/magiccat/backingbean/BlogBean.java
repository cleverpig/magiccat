package org.magiccat.backingbean;

import com.icefaces.model.datamodel.PaginationDataModel;
import com.icefaces.model.datamodel.SingleDAOServiceDataModel;
import org.apache.commons.lang.StringUtils;
import org.magiccat.backingbean.base.OnePageOneAppBean;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.Blog;
import org.magiccat.domain.SiteUser;
import org.magiccat.service.BlogService;
import org.magiccat.service.SiteUserService;
import org.magiccat.util.QueryHelper;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午8:08
 * To change this template use File | Settings | File Templates.
 */
public class BlogBean extends OnePageOneAppBean{
  private BlogService blogService;
  private Blog blog;
  private SingleDAOServiceDataModel<Blog> listData;
  private String queryTitle;
  private SiteUserService siteUserService;

  public BlogBean() {
    super("publishDate");
  }

  public BlogService getBlogService() {
    return blogService;
  }

  public void setBlogService(BlogService blogService) {
    this.blogService = blogService;
  }

  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }

  public String getQueryTitle() {
    return queryTitle;
  }

  public void setQueryTitle(String queryTitle) {
    this.queryTitle = queryTitle;
  }

  public SingleDAOServiceDataModel<Blog> getListData() {
    return listData;
  }

  public void setListData(SingleDAOServiceDataModel<Blog> listData) {
    this.listData = listData;
  }

  public SiteUserService getSiteUserService() {
    return siteUserService;
  }

  public void setSiteUserService(SiteUserService siteUserService) {
    this.siteUserService = siteUserService;
  }

  public void saveActionListener(ActionEvent event){
    SiteUser publisher=siteUserService.loadByUserId("cleverpig");//TODO:please change this hard-coding publisher
    blogService.saveNewBlog(blog,publisher);
  }

  @Override
  @PostConstruct
  public void loadData() {
    if (listData==null){
      listData=new SingleDAOServiceDataModel<Blog>(
          blogService,getPageSize(),constructQueryConditions(),constructOrderConditions()
      );
    }
  }

  @Override
  public List<QueryCondition> constructQueryConditions() {
    List<QueryCondition> queryConditions=new ArrayList<QueryCondition>(1);
    if (StringUtils.isNotEmpty(queryTitle)){
      queryConditions.add(QueryHelper.constructLikeCondition("title", queryTitle, QueryCondition.AND_RELATION));
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
  public boolean isDefaultAscending(String sortColumn) {
    return false;
  }

  @Override
  public void notifyDataModelChange() {
    listData.setQueryConditions(constructQueryConditions());
    listData.setOrderConditions(constructOrderConditions());
    listData.setDirtyData(true);
  }
}
