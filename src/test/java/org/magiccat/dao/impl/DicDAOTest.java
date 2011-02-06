package org.magiccat.dao.impl;

import org.magiccat.dao.DicDAO;
import org.magiccat.domain.Dic;
import org.magiccat.test.AbstractBaseTestCase;

import java.util.List;

import static org.magiccat.test.TestConstants.TEST_APPLICATION_CONTEXT_PATH;
/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-6
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class DicDAOTest extends AbstractBaseTestCase<DicDAO>{

  public DicDAOTest() {
    super("dicDAO", TEST_APPLICATION_CONTEXT_PATH);
  }

  public void testLoadByCatTypesAndEntryId() throws Exception {
    DicDAO dao=getBean();
    Dic dic=dao.loadByCatTypesAndEntryId("01","01");
    assertNotNull(dic);
  }

  public void testListByCatTypes() throws Exception {
    DicDAO dao=getBean();
    List<Dic> dics=dao.listByCatTypes("01");
    assertNotNull(dics);
    assertTrue(dics.size()>0);
  }
}
