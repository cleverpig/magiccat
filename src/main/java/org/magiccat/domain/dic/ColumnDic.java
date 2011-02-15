package org.magiccat.domain.dic;

import org.magiccat.domain.Article;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-15
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
@Entity
@DiscriminatorValue("COLUMN")
public class ColumnDic extends Dic{
  @OneToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      fetch = FetchType.LAZY,
      mappedBy = "column"
  )
  private List<Article> articles;

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }
}
