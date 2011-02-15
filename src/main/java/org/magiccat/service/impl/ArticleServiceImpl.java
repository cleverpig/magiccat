package org.magiccat.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.magiccat.dao.ArticleDAO;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.Article;
import org.magiccat.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 上午11:13
 * To change this template use File | Settings | File Templates.
 */
public class ArticleServiceImpl extends AbstractSingleDAOService<ArticleDAO,Article,Integer> implements ArticleService{

  private Log log= LogFactory.getLog(ArticleServiceImpl.class);

  @Override
  public void saveNew(Article article)
  {
    dao.save(article);
  }

  @Override
  public void update(Article article) {
    dao.update(article);
  }

  @Override
  public void deleteById(Integer id) {
    Article article=dao.load(id);
    if (article!=null)
      dao.delete(article);
  }

  @Override
  public Article loadById(Integer id) {
    return dao.load(id);
  }

  @Override
  public Boolean isRecordExist(List<QueryCondition> queryConditions) {
    return (dao.count(queryConditions)>0)?true:false;
  }

  @Override
  public Boolean isRecordExist(QueryCondition queryCondition) {
    List<QueryCondition> queryConditions=new ArrayList<QueryCondition>(1);
    queryConditions.add(queryCondition);
    return isRecordExist(queryConditions);
  }

  @Override
  public Long count(List<QueryCondition> queryConditions) {
    return dao.count(queryConditions);
  }

  @Override
  public List<Article> queryPagedResult(
      List<QueryCondition> queryConditions, List<OrderCondition> orderConditions,
      int startRow,int pageSize) {
    return dao.queryPagedResult(queryConditions,orderConditions,startRow,pageSize);
  }
}
