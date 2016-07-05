package spring.core.entity;

import javax.persistence.*;

@Entity
public class EventStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "event_id")
    private int eventId;

    @Column(name = "count_by_event_name")
    private int countByEventName;

    @Column(name = "count_by_event_price")
    private int countByEventPrice;

    @Column(name = "count_by_ticket_booked")
    private int countByTicketBooked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getCountByEventName() {
        return countByEventName;
    }

    public void setCountByEventName(int countByEventName) {
        this.countByEventName = countByEventName;
    }

    public int getCountByEventPrice() {
        return countByEventPrice;
    }

    public void setCountByEventPrice(int countByEventPrice) {
        this.countByEventPrice = countByEventPrice;
    }

    public int getCountByTicketBooked() {
        return countByTicketBooked;
    }

    public void setCountByTicketBooked(int countByTicketBooked) {
        this.countByTicketBooked = countByTicketBooked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventStat eventStat = (EventStat) o;

        return id == eventStat.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "EventStat{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", countByEventName=" + countByEventName +
                ", countByEventPrice=" + countByEventPrice +
                ", countByTicketBooked=" + countByTicketBooked +
                '}';
    }
}
