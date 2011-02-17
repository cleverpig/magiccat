package org.magiccat.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.magiccat.dao.BlogDAO;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.Blog;
import org.magiccat.service.BlogService;

import java.util.ArrayList;
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

  @Override
  public void saveNew(Blog blog)
  {
    dao.save(blog);
  }

  @Override
  public void update(Blog blog) {
    dao.update(blog);
  }

  @Override
  public void deleteById(Integer id) {
    Blog blog =dao.load(id);
    if (blog !=null)
      dao.delete(blog);
  }

  @Override
  public Blog loadById(Integer id) {
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
  public List<Blog> queryPagedResult(
      List<QueryCondition> queryConditions, List<OrderCondition> orderConditions,
      int startRow,int pageSize) {
    return dao.queryPagedResult(queryConditions,orderConditions,startRow,pageSize);
  }
}
