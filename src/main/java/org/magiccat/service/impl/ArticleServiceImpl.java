package org.magiccat.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.magiccat.dao.ArticleDAO;
import org.magiccat.domain.Article;
import org.magiccat.service.ArticleService;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 上午11:13
 * To change this template use File | Settings | File Templates.
 */
public class ArticleServiceImpl implements ArticleService{
  private ArticleDAO articleDAO;
  private Log log= LogFactory.getLog(ArticleServiceImpl.class);

  public ArticleDAO getArticleDAO() {
    return articleDAO;
  }

  public void setArticleDAO(ArticleDAO articleDAO) {
    this.articleDAO = articleDAO;
  }

  @Override
  public void saveNewArticle(Article article)
  {
    articleDAO.save(article);
  }
}
