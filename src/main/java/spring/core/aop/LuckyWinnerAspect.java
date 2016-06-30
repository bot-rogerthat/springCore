package spring.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.core.entity.Event;
import spring.core.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Aspect
public class LuckyWinnerAspect {
    private static final Logger log = LoggerFactory.getLogger(LuckyWinnerAspect.class);
    private final List<Object> luckyGuys = new ArrayList<>();

    @Around(value = "execution(* spring.core.service.BookingService.getTicketPrice(..)) && args(event, seat, user)",
            argNames = "joinPoint,user,seat,event")
    public BigDecimal checkLucky(ProceedingJoinPoint joinPoint, User user, String seat, Event event) throws AspectException {
        if (isLucky()) {
            luckyGuys.add(new Object[]{user, event});
            return new BigDecimal(0);
        }
        try {
            return (BigDecimal) joinPoint.proceed(new Object[]{event, seat, user});
        } catch (Throwable e) {
            log.error("proceed is failed", e);
            throw new AspectException(e);
        }
    }

    private boolean isLucky() {
        return Math.random() > 0.5;
    }
}

