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

  @ManyToOne(
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "eventReviewerId")
  private SiteUser eventReviewer;

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public SiteUser getEventReviewer() {
    return eventReviewer;
  }

  public void setEventReviewer(SiteUser eventReviewer) {
    this.eventReviewer = eventReviewer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EventComment)) return false;
    if (!super.equals(o)) return false;

    EventComment that = (EventComment) o;

    if (event != null ? !event.equals(that.event) : that.event != null) return false;
    if (eventReviewer != null ? !eventReviewer.equals(that.eventReviewer) : that.eventReviewer != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (event != null ? event.hashCode() : 0);
    result = 31 * result + (eventReviewer != null ? eventReviewer.hashCode() : 0);
    return result;
  }
}
