package spring.core.entity.discount.impl;

import spring.core.entity.Event;
import spring.core.entity.User;
import spring.core.entity.discount.DiscountStrategy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class DiscountBirthday implements DiscountStrategy {
    private BigDecimal value;

    public DiscountBirthday(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal apply(User user, Event event) {
        return checkMonthAndDay(user.getDayOfBirth(), event.getDate()) ? value : new BigDecimal(1.0);
    }

    private boolean checkMonthAndDay(Timestamp target, Timestamp dest) {
        //// TODO: 6/22/2016 fix
        return target.getMonth() == dest.getMonth()
                && target.getDay() == dest.getDay();
    }

    @Override
    public String toString() {
        return "DiscountBirthday{" +
                "value=" + value +
                '}';
    }
}
