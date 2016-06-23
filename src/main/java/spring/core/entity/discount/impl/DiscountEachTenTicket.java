package spring.core.entity.discount.impl;

import spring.core.entity.Event;
import spring.core.entity.User;
import spring.core.entity.discount.DiscountStrategy;

import java.math.BigDecimal;

public class DiscountEachTenTicket implements DiscountStrategy {
    private BigDecimal value;

    public DiscountEachTenTicket(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal apply(User user, Event event) {
        int countTickets = user.getTickets().size();
        return (countTickets != 0 && countTickets % 9 == 0) ? value : BigDecimal.ONE;
    }

    @Override
    public String toString() {
        return "DiscountEachTenTicket{" +
                "value=" + value +
                '}';
    }
}
