package spring.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "auditorium")
public class Auditorium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> vips;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Integer> getVips() {
        return vips;
    }

    public void setVips(List<Integer> vips) {
        this.vips = vips;
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", vips=" + vips +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Auditorium that = (Auditorium) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
