package org.magiccat.dao.impl;

import org.magiccat.dao.BlogDAO;
import org.magiccat.domain.Blog;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 上午11:11
 * To change this template use File | Settings | File Templates.
 */
public class BlogDAOImpl extends AbstractHibernateBaseDAOImpl<Blog,Integer> implements BlogDAO {
  @Override
  public Integer save(Blog entity) {
    entity.setModifyDate(null);
    entity.setPublishDate(new Date());
    return super.save(entity);
  }

  @Override
  public void update(Blog entity) {
    entity.setModifyDate(new Date());
    super.update(entity);
  }
}
