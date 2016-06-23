package spring.core.entity.discount.impl;

import spring.core.entity.Event;
import spring.core.entity.User;
import spring.core.entity.discount.DiscountStrategy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DiscountBirthday implements DiscountStrategy {
    private BigDecimal value;

    public DiscountBirthday(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal apply(User user, Event event) {
        return checkMonthAndDay(user.getDayOfBirth(), event.getDate()) ? value : BigDecimal.ONE;
    }

    private boolean checkMonthAndDay(Timestamp target, Timestamp dest) {
        String left = new SimpleDateFormat("MM-dd").format(target);
        String right = new SimpleDateFormat("MM-dd").format(dest);
        return left.equals(right);
    }

    @Override
    public String toString() {
        return "DiscountBirthday{" +
                "value=" + value +
                '}';
    }
}
