package org.magiccat.dao.impl;

import org.magiccat.dao.SiteUserDAO;
import org.magiccat.domain.SiteUser;
import org.magiccat.test.AbstractMultiBeansTestCase;
import static org.magiccat.test.TestConstants.TEST_APPLICATION_CONTEXT_PATH;
import static org.magiccat.dao.impl.BeanNameConstants.*;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-18
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
public class SiteUserDAOTest extends AbstractMultiBeansTestCase{
  public SiteUserDAOTest() {
    super(TEST_APPLICATION_CONTEXT_PATH,SITEUSER_DAO_BEAN_NAME);
  }

  public void testSaveNew() throws Exception {
    SiteUserDAO dao= (SiteUserDAO) getBean(SITEUSER_DAO_BEAN_NAME);
    SiteUser siteUser=new SiteUser();
    siteUser.setUserId("john");
    siteUser.setNickName("约翰");
    dao.save(siteUser);
  }

  public void testLoadByUserId() throws Exception {
    SiteUserDAO dao= (SiteUserDAO) getBean(SITEUSER_DAO_BEAN_NAME);
    SiteUser siteUser=dao.loadByUserId("cleverpig");
    assertNotNull(siteUser);
  }
}
