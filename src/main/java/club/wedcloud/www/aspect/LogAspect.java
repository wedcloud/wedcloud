package club.wedcloud.www.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author xuhb
 * @Description LogAspect, controller全局AOP切面定义
 * @time 2019年10月28日
 */
@Aspect
@Component
@Order(1)
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // 定义切点
    @Pointcut("execution(public * club.wedcloud.www.controller.*.*(..))")
    public void webLog() {
    }

    // 环绕增强
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("方法环绕");
        Object o = pjp.proceed();
        logger.info("方法环绕proceed，结果是 :" + o);
        return o;
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收请求，获取请求内容
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    // 请求完成，返回值
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        logger.info("方法返回值：" + ret);
    }

    // 请求完成，异常
    @AfterThrowing(pointcut = "webLog()")
    public void throwss(JoinPoint joinPoint) {
        logger.info("方法异常：" + joinPoint);
    }

    // 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint joinPoint) {
        logger.info("方法最后执行：" + joinPoint);
    }
}
