package org.magiccat.service;

import org.magiccat.dao.SiteUserDAO;
import org.magiccat.domain.SiteUser;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-18
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
public interface SiteUserService extends SingleDAOService<SiteUserDAO,SiteUser,Integer>{
  public SiteUser loadByUserId(String userId);
}
