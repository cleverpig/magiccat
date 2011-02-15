package org.magiccat.service;

import org.magiccat.dao.ArticleDAO;
import org.magiccat.domain.Article;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
public interface ArticleService extends SingleDAOService<ArticleDAO,Article,Integer> {
}
