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

  @ManyToMany
  @JoinTable(
      name="event_participator",
      joinColumns={@JoinColumn(name="event_id")},
      inverseJoinColumns = {@JoinColumn(name="paricipator_id")}
  )
  private Set<Participator> participators;

  public List<EventComment> getComments() {
    return comments;
  }

  public void setComments(List<EventComment> comments) {
    this.comments = comments;
  }

  public Set<Participator> getParticipators() {
    return participators;
  }

  public void setParticipators(Set<Participator> participators) {
    this.participators = participators;
  }
}
