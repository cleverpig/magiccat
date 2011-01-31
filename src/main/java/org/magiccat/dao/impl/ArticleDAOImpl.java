package org.magiccat.dao.impl;

import org.magiccat.dao.AbstractHibernateBaseDAO;
import org.magiccat.dao.ArticleDAO;
import org.magiccat.domain.Article;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 上午11:11
 * To change this template use File | Settings | File Templates.
 */
public class ArticleDAOImpl extends AbstractHibernateBaseDAOImpl<Article,Integer> implements ArticleDAO {
  @Override
  public Integer save(Article entity) {
    entity.setModifyDate(null);
    entity.setPublishDate(new Date());
    return super.save(entity);
  }

  @Override
  public void update(Article entity) {
    entity.setModifyDate(new Date());
    super.update(entity);
  }
}
