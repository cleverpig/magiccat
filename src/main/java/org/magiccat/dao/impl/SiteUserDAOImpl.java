package org.magiccat.dao.impl;

import org.magiccat.dao.SiteUserDAO;
import org.magiccat.domain.SiteUser;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-18
 * Time: 上午10:54
 * To change this template use File | Settings | File Templates.
 */
public class SiteUserDAOImpl extends AbstractHibernateBaseDAOImpl<SiteUser,Integer> implements SiteUserDAO{
  @Override
  public SiteUser loadByUserId(String userId) {
    SiteUser exampleUser=new SiteUser();
    exampleUser.setUserId(userId);
    return loadByExample(exampleUser);
  }
}
