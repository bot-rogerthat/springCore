package spring.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import spring.core.entity.User;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Aspect
public class DiscountAspect {
    private Map<String, Integer> counterByDiscountStrategy = new HashMap<>();
    private Map<User, Integer> counterByUserDiscount = new HashMap<>();

    @AfterReturning(pointcut = "execution(* spring.core.entity.discount.DiscountStrategy.apply(..))", returning = "discount")
    public void addCountByDiscountStrategy(JoinPoint joinPoint, BigDecimal discount) {
        String type = joinPoint.getTarget().getClass().getSimpleName();
        if (!discount.equals(BigDecimal.ONE)) {
            int count = counterByDiscountStrategy.getOrDefault(type, 0);
            counterByDiscountStrategy.put(type, ++count);
        }
    }

    @AfterReturning(pointcut = "execution(* spring.core.service.DiscountService.getDiscount(..)) && args(user,..)",
            returning = "discount", argNames = "user,discount")
    public void addCountEventByName(User user, BigDecimal discount) {
        if (!discount.equals(BigDecimal.ONE)) {
            int count = counterByUserDiscount.getOrDefault(user, 0);
            counterByUserDiscount.put(user, ++count);
        }
    }
}
