package org.magiccat.service.impl;

import org.magiccat.dao.SiteUserDAO;
import org.magiccat.domain.SiteUser;
import org.magiccat.service.SiteUserService;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-18
 * Time: 下午3:09
 * To change this template use File | Settings | File Templates.
 */
public class SiteUserServiceImpl extends AbstractSingleDAOService<SiteUserDAO,SiteUser,Integer> implements SiteUserService{
  @Override
  public SiteUser loadByUserId(String userId) {
    return dao.loadByUserId(userId);
  }
}
