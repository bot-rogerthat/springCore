package spring.core.entity;

public class Ticket {
    private String id;
    private String seat;
    private boolean isBooked;
    private Event event;
    private User user;

    public Ticket(String seat) {
        this.seat = seat;
        this.isBooked = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", seat='" + seat + '\'' +
                ", isBooked=" + isBooked +
                ", event=" + event +
                ", user=" + user +
                '}';
    }
}
