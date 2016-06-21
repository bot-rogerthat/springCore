package spring.core.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String email;
    private String dayOfBirth;
    private List<Ticket> tickets;

    public User(String name, String email, String dayOfBirth) {
        this.name = name;
        this.email = email;
        this.dayOfBirth = dayOfBirth;
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket target) {
        tickets.add(target);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                '}';
    }
}
