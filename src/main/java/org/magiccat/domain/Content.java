package org.magiccat.domain;

import org.hibernate.annotations.GenericGenerator;
import org.magiccat.domain.dic.ColumnDic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-17
 * Time: 下午1:25
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"ALL"})
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Content implements Serializable {
  @Id
  @GeneratedValue(generator = "idGen")
  @GenericGenerator(name = "idGen", strategy = "increment")
  private Integer id;//note:at here,GenerationType.AUTO or GenerationType.Identify weren't permitted,reference: http://stackoverflow.com/questions/916169/cannot-use-identity-column-key-generation-with-union-subclass-table-per-class

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="columnId")
  private ColumnDic column;

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

  public ColumnDic getColumn() {
    return column;
  }

  public void setColumn(ColumnDic column) {
    this.column = column;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Content)) return false;

    Content content = (Content) o;

    if (publishDate != null ? !publishDate.equals(content.publishDate) : content.publishDate != null) return false;
    if (title != null ? !title.equals(content.title) : content.title != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
    return result;
  }

}
