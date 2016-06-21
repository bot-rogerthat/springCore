package spring.core.service;

import spring.core.entity.Event;
import spring.core.entity.User;
import spring.core.entity.discount.DiscountStrategy;

import java.util.List;

public class DiscountService {
    private List<DiscountStrategy> discounts;

    public Double getDiscount(User user, Event event) {
        Double discount = 1.0;
        for (DiscountStrategy dis : discounts) {
            discount *= dis.apply(user, event);
        }
        return discount;
    }

    public void setDiscounts(List<DiscountStrategy> discounts) {
        this.discounts = discounts;
    }

    public List<DiscountStrategy> getDiscounts() {
        return discounts;
    }
}
