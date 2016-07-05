package spring.core.service;

import org.springframework.stereotype.Service;
import spring.core.entity.Event;
import spring.core.entity.User;
import spring.core.entity.discount.DiscountStrategy;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountService {
    private List<DiscountStrategy> discounts;

    public BigDecimal getDiscount(User user, Event event) {
        BigDecimal discount = new BigDecimal(1.0);
        for (DiscountStrategy dis : discounts) {
            discount = discount.multiply(dis.apply(user, event));
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
