package spring.core.entity.discount;

import spring.core.entity.Event;
import spring.core.entity.User;

public interface DiscountStrategy {
    Double apply(User user, Event event);
}
