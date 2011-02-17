package org.magiccat.domain;

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
@Table(name="participator")
public class Participator implements Serializable{
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
  private Set<Event> events;

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

  public Set<Event> getEvents() {
    return events;
  }

  public void setEvents(Set<Event> events) {
    this.events = events;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Participator)) return false;

    Participator that = (Participator) o;

    if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null) return false;
    if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = userId != null ? userId.hashCode() : 0;
    result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
    return result;
  }
}
