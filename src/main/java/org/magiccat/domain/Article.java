package org.magiccat.domain;

import org.springframework.security.core.userdetails.User;
import javax.persistence.*;
import java.sql.Clob;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
@Entity(name = "article")
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false,length = 200)
  private String title;

  @Lob
  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date publishDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date modifyDate;

  @Column(nullable = false,length = 20)
  private String author;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
  }

  public Date getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Article)) return false;

    Article article = (Article) o;

    if (publishDate != null ? !publishDate.equals(article.publishDate) : article.publishDate != null) return false;
    if (title != null ? !title.equals(article.title) : article.title != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
    return result;
  }
}
