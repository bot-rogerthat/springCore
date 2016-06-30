package spring.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.DiscountStat;
import spring.core.entity.User;
import spring.core.service.DiscountStatService;

import java.math.BigDecimal;

@Aspect
public class DiscountAspect {
    private DiscountStatService discountStatService;

    @AfterReturning(pointcut = "execution(* spring.core.entity.discount.DiscountStrategy.apply(..)) && args(user,..)", returning = "discount")
    public void addCountByDiscountStrategy(JoinPoint joinPoint, User user, BigDecimal discount) throws DaoException {
        String type = joinPoint.getTarget().getClass().getSimpleName();
        if (!discount.equals(BigDecimal.ONE)) {
            DiscountStat discountStat = discountStatService.getDiscountStat(user, type);
            discountStat.setCount(discountStat.getCount() + 1);
            discountStatService.updateStat(discountStat);
        }
    }

    public DiscountStatService getDiscountStatService() {
        return discountStatService;
    }

    public void setDiscountStatService(DiscountStatService discountStatService) {
        this.discountStatService = discountStatService;
    }
}
