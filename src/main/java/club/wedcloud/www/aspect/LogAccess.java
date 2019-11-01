package club.wedcloud.www.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAccess {

  private static final Logger logger = LoggerFactory.getLogger(LogAccess.class);

  @Pointcut("@annotation(club.wedcloud.www.aspect.annotation.LogAccess)")
  public void logAccess() {}

  @Before("logAccess()")
  public void doBefore(JoinPoint joinPoint) {
    logger.info("before:" + joinPoint);
  }

}
