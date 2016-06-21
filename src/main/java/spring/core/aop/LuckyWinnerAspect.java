package spring.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import spring.core.entity.Event;
import spring.core.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LuckyWinnerAspect {
    private List<Object> luckyGuys = new ArrayList<>();

    @Around(value = "execution(* spring.core.service.BookingService.getTicketPrice(..)) && args(event, seat, user)",
            argNames = "joinPoint,user,seat,event")
    public BigDecimal checkLucky(ProceedingJoinPoint joinPoint, User user, String seat, Event event) throws Throwable {
        if (isLucky()) {
            luckyGuys.add(new Object[]{user, event});
            return new BigDecimal(0);
        }
        return (BigDecimal) joinPoint.proceed(new Object[]{event, seat, user});
    }

    private boolean isLucky() {
        return Math.random() > 0.5;
    }
}

