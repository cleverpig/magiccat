package org.magiccat.dao.impl;

import org.magiccat.dao.ArticleDAO;
import org.magiccat.dao.ColumnDicDAO;
import org.magiccat.domain.Article;
import org.magiccat.domain.dic.ColumnDic;
import org.magiccat.test.AbstractMultiBeansTestCase;

import static org.magiccat.test.TestConstants.TEST_APPLICATION_CONTEXT_PATH;
/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class ArticleDAOImplTest extends AbstractMultiBeansTestCase {
  private static final String ARTICLE_DAO_BEAN_NAME="articleDAO";
  private static final String COLUMN_DAO_BEAN_NAME="columnDicDAO";
  public ArticleDAOImplTest() {
    super(TEST_APPLICATION_CONTEXT_PATH,ARTICLE_DAO_BEAN_NAME,COLUMN_DAO_BEAN_NAME);
  }

  public void testSave() throws Exception {
    ArticleDAO dao= (ArticleDAO) getBean(ARTICLE_DAO_BEAN_NAME);
    Article article=new Article();
    article.setAuthor("cleverpig");
    article.setContent("new content");
    article.setTitle("new title");

    ColumnDicDAO columnDicDAO= (ColumnDicDAO) getBean(COLUMN_DAO_BEAN_NAME);
    ColumnDic columnDic=columnDicDAO.load(1);
    article.setColumn(columnDic);

    dao.save(article);
  }
}
