package spring.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import spring.core.dao.impl.jdbc.DaoException;

import java.util.Arrays;

@Aspect
public class DaoExceptionAspect {
    private static final Logger log = LoggerFactory.getLogger(DaoExceptionAspect.class);

    @AfterThrowing(pointcut = "execution(* spring.core.dao.*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, DataAccessException ex) throws DaoException {
        log.error("Thrown DataAccessException", joinPoint.getSignature() + Arrays.toString(joinPoint.getArgs()), ex);
        throw new DaoException("Dao error", ex);
    }
}
