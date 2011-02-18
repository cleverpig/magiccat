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

  @ManyToOne(
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "blogReviewerId")
  private SiteUser blogReviewer;

  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }

  public SiteUser getBlogReviewer() {
    return blogReviewer;
  }

  public void setBlogReviewer(SiteUser blogReviewer) {
    this.blogReviewer = blogReviewer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BlogComment)) return false;
    if (!super.equals(o)) return false;

    BlogComment that = (BlogComment) o;

    if (blog != null ? !blog.equals(that.blog) : that.blog != null) return false;
    if (blogReviewer != null ? !blogReviewer.equals(that.blogReviewer) : that.blogReviewer != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (blog != null ? blog.hashCode() : 0);
    result = 31 * result + (blogReviewer != null ? blogReviewer.hashCode() : 0);
    return result;
  }
}
