package org.magiccat.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-17
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"ALL"})
@Entity
@Table(name="BlogComment")
public class BlogComment extends Comment{
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="blogId")
  private Blog blog;

  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }
}
