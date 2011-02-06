package org.magiccat.dao.impl;

import com.mysql.jdbc.Clob;
import junit.framework.TestCase;
import org.magiccat.dao.ArticleDAO;
import org.magiccat.domain.Article;
import org.magiccat.test.AbstractBaseTestCase;
import static org.magiccat.test.TestConstants.*;
/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class ArticleDAOImplTest extends AbstractBaseTestCase<ArticleDAO> {

  public ArticleDAOImplTest() {
    super("articleDAO",TEST_APPLICATION_CONTEXT_PATH);
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
