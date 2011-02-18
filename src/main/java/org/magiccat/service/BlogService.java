package org.magiccat.service;

import org.magiccat.dao.BlogDAO;
import org.magiccat.domain.Blog;
import org.magiccat.domain.SiteUser;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
public interface BlogService extends SingleDAOService<BlogDAO,Blog,Integer> {
  public void saveNewBlog(Blog blog,SiteUser publisher);
}
