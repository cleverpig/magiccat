package org.magiccat.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-17
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"ALL"})
@Entity
@Table(name="SiteUser")
public class SiteUser implements Serializable{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  @Column(nullable = false,length=50)
  private String userId;
  @Column(nullable = false,length=50)
  private String nickName;

  @ManyToMany(
      mappedBy = "participators"
  )
  private Set<Event> joinedEvents;

  @OneToMany(
      targetEntity =BlogComment.class,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      mappedBy = "blogReviewer"
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Set<BlogComment> blogComments;

  @OneToMany(
      targetEntity =EventComment.class,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      mappedBy = "eventReviewer"
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Set<EventComment> eventComments;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "manager"
  )
  private Set<Event> managedEvents;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "publisher"
  )
  private Set<Blog> publishedBlog;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public Set<Event> getJoinedEvents() {
    return joinedEvents;
  }

  public void setJoinedEvents(Set<Event> joinedEvents) {
    this.joinedEvents = joinedEvents;
  }

  public Set<Event> getManagedEvents() {
    return managedEvents;
  }

  public void setManagedEvents(Set<Event> managedEvents) {
    this.managedEvents = managedEvents;
  }

  public Set<BlogComment> getBlogComments() {
    return blogComments;
  }

  public void setBlogComments(Set<BlogComment> blogComments) {
    this.blogComments = blogComments;
  }

  public Set<EventComment> getEventComments() {
    return eventComments;
  }

  public void setEventComments(Set<EventComment> eventComments) {
    this.eventComments = eventComments;
  }

  public Set<Blog> getPublishedBlog() {
    return publishedBlog;
  }

  public void setPublishedBlog(Set<Blog> publishedBlog) {
    this.publishedBlog = publishedBlog;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SiteUser)) return false;

    SiteUser siteUser = (SiteUser) o;

    if (userId != null ? !userId.equals(siteUser.userId) : siteUser.userId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return userId != null ? userId.hashCode() : 0;
  }
}
