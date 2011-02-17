package org.magiccat.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"ALL"})
@Entity
@Table(name="Blog")
public class Blog extends Content{
  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      mappedBy = "blog"
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Set<BlogComment> comments;

  public Set<BlogComment> getComments() {
    return comments;
  }

  public void setComments(Set<BlogComment> comments) {
    this.comments = comments;
  }
}
