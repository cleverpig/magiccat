package org.magiccat.backingbean;

import org.magiccat.domain.Blog;
import org.magiccat.service.BlogService;

import javax.faces.event.ActionEvent;


/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午8:08
 * To change this template use File | Settings | File Templates.
 */
public class BlogBean {
  private BlogService blogService;
  private Blog blog;
  private Boolean showEditor;
  private Boolean showQuery;

  public BlogBean() {
    blog =new Blog();
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

  public Boolean getShowEditor() {
    return showEditor;
  }

  public void setShowEditor(Boolean showEditor) {
    this.showEditor = showEditor;
  }

  public Boolean getShowQuery() {
    return showQuery;
  }

  public void setShowQuery(Boolean showQuery) {
    this.showQuery = showQuery;
  }

  public void saveActionListener(ActionEvent event){
    blogService.saveNew(blog);

  }
}
