package spring.core.entity.discount.impl;

import spring.core.entity.Event;
import spring.core.entity.User;
import spring.core.entity.discount.DiscountStrategy;

public class DiscountEachTenTicket implements DiscountStrategy {
    private Double value;

    public DiscountEachTenTicket(Double value) {
        this.value = value;
    }

    @Override
    public Double apply(User user, Event event) {
        return user.getDayOfBirth().equals(event.getDate()) ? value : 1.0;
    }

    @Override
    public String toString() {
        return "DiscountEachTenTicket{" +
                "value=" + value +
                '}';
    }
}
