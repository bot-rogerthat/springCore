package spring.core.entity.discount.impl;

import spring.core.entity.Event;
import spring.core.entity.User;
import spring.core.entity.discount.DiscountStrategy;

import java.math.BigDecimal;

public class DiscountBirthday implements DiscountStrategy {
    private BigDecimal value;

    public DiscountBirthday(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal apply(User user, Event event) {
        return user.getDayOfBirth().equals(event.getDate()) ? value : new BigDecimal(1.0);
    }

    @Override
    public String toString() {
        return "DiscountBirthday{" +
                "value=" + value +
                '}';
    }
}
