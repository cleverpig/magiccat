package org.magiccat.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.magiccat.dao.BlogDAO;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.Blog;
import org.magiccat.domain.SiteUser;
import org.magiccat.service.BlogService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 上午11:13
 * To change this template use File | Settings | File Templates.
 */
public class BlogServiceImpl extends AbstractSingleDAOService<BlogDAO,Blog,Integer> implements BlogService {

  private Log log= LogFactory.getLog(BlogServiceImpl.class);
  private BlogDAO blogDAO;

  @Override
  public void saveNewBlog(Blog blog, SiteUser publisher) {
    blog.setPublishDate(new Date());
    blog.setPublisher(publisher);
    blogDAO.save(blog);
  }

}
