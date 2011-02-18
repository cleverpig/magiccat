package org.magiccat.dao;

import org.magiccat.domain.SiteUser;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-18
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public interface SiteUserDAO  extends AbstractHibernateBaseDAO<SiteUser,Integer> {
  public SiteUser loadByUserId(String userId);
}
