package spring.core.entity.discount;

import spring.core.entity.Event;
import spring.core.entity.User;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal apply(User user, Event event);
}
