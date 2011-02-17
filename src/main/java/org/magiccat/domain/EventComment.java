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
@Table(name="EventComment")
public class EventComment extends Comment{
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="blogId")
  private Event event;

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }
}
