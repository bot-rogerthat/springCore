package spring.core.entity;

public class EventStat {
    private int id;
    private int eventId;
    private int countByEventName;
    private int countByEventPrice;
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
