package org.magiccat.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-17
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"ALL"})
@Entity
@Table(name="Event")
public class Event extends Content{
  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      mappedBy = "event"
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<EventComment> comments;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="managerId")
  private SiteUser manager;

  @ManyToMany
  @JoinTable(
      name="event_paricipator",
      joinColumns={@JoinColumn(name="eventId")},
      inverseJoinColumns = {@JoinColumn(name="paricipatorId")}
  )
  private Set<SiteUser> participators;

  public List<EventComment> getComments() {
    return comments;
  }

  public void setComments(List<EventComment> comments) {
    this.comments = comments;
  }

  public SiteUser getManager() {
    return manager;
  }

  public void setManager(SiteUser manager) {
    this.manager = manager;
  }

  public Set<SiteUser> getParticipators() {
    return participators;
  }

  public void setParticipators(Set<SiteUser> participators) {
    this.participators = participators;
  }
}
