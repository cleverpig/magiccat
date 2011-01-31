package org.magiccat.dao.impl;

import com.mysql.jdbc.Clob;
import junit.framework.TestCase;
import org.magiccat.dao.ArticleDAO;
import org.magiccat.domain.Article;
import org.magiccat.test.AbstractBaseTestCase;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class ArticleDAOImplTest extends AbstractBaseTestCase<ArticleDAO> {

  public ArticleDAOImplTest() {
    super("articleDAO","org/magiccat/config/applicationContext.xml");
  }

  public void testSave() throws Exception {
    ArticleDAO dao=getBean();
    Article entity=new Article();
    entity.setAuthor("cleverpig");
    entity.setContent("new content");
    entity.setTitle("new title");
    dao.save(entity);
  }
}
