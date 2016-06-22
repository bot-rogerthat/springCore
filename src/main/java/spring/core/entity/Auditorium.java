package spring.core.entity;

import java.util.Random;
import java.util.Set;

public class Auditorium {
    public int id;
    public String name;
    public int numberOfSeats;
    public Set<String> vips;

    public Auditorium(String name, int numberOfSeats, Set<String> vips) {
        this.id = new Random().nextInt();
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vips = vips;
    }

    public boolean isVip(String target) {
        return vips.contains(target);
    }

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

    public Set<String> getVips() {
        return vips;
    }

    public void setVips(Set<String> vips) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auditorium that = (Auditorium) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
