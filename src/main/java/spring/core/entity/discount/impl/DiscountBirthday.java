package spring.core.entity.discount.impl;

import spring.core.entity.Event;
import spring.core.entity.User;
import spring.core.entity.discount.DiscountStrategy;

public class DiscountBirthday implements DiscountStrategy {
    private Double value;

    public DiscountBirthday(Double value) {
        this.value = value;
    }

    @Override
    public Double apply(User user, Event event) {
        int countTickets = user.getTickets().size();
        return (countTickets != 0 && countTickets % 9 == 0) ? value : 1.0;
    }

    @Override
    public String toString() {
        return "DiscountBirthday{" +
                "value=" + value +
                '}';
    }
}
