package org.magiccat.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-17
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"ALL"})
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Comment implements Serializable{
  @Id
  @GeneratedValue(generator = "idGen")
  @GenericGenerator(name = "idGen",strategy = "increment")
  private Integer id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date commentTime;
  @Column(nullable = false,length = 200)
  private String content;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Date getCommentTime() {
    return commentTime;
  }

  public void setCommentTime(Date commentTime) {
    this.commentTime = commentTime;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Comment)) return false;

    Comment comment = (Comment) o;

    if (commentTime != null ? !commentTime.equals(comment.commentTime) : comment.commentTime != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return commentTime != null ? commentTime.hashCode() : 0;
  }
}
