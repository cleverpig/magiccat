package org.magiccat.backingbean;

import org.magiccat.domain.Article;
import org.magiccat.service.ArticleService;

import javax.faces.event.ActionEvent;


/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午8:08
 * To change this template use File | Settings | File Templates.
 */
public class ArticleBean {
  private ArticleService articleService;
  private Article article;
  private Boolean showEditor;
  private Boolean showQuery;

  public ArticleBean() {
    article=new Article();
  }

  public ArticleService getArticleService() {
    return articleService;
  }

  public void setArticleService(ArticleService articleService) {
    this.articleService = articleService;
  }

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  public Boolean getShowEditor() {
    return showEditor;
  }

  public void setShowEditor(Boolean showEditor) {
    this.showEditor = showEditor;
  }

  public Boolean getShowQuery() {
    return showQuery;
  }

  public void setShowQuery(Boolean showQuery) {
    this.showQuery = showQuery;
  }

  public void saveActionListener(ActionEvent event){
    articleService.saveNew(article);

  }
}
