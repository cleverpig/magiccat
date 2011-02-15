package org.magiccat.dao.impl;

import org.magiccat.dao.ColumnDicDAO;
import org.magiccat.domain.dic.ColumnDic;
import org.magiccat.test.AbstractSingleBeanTestCase;
import org.magiccat.test.TestConstants;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-15
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
public class ColumnDicDAOTest extends AbstractSingleBeanTestCase<ColumnDicDAO> {
  public ColumnDicDAOTest() {
    super("columnDicDAO", TestConstants.TEST_APPLICATION_CONTEXT_PATH);
  }

  public void testSaveNew() throws Exception {
    ColumnDicDAO dao=getBean();
    ColumnDic columnDic=new ColumnDic();
    columnDic.setArticles(null);
    columnDic.setEnabled(true);
    columnDic.setEntryId("01");
    columnDic.setEntryOrder("01");
    columnDic.setEntryVal("01");

    dao.save(columnDic);
  }
}
