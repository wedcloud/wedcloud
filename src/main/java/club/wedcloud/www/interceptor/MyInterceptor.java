package club.wedcloud.www.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的拦截器在中，拦截器的方法将按照 preHandle -> Controller -> posthandle -> afterCompletion 的顺序执行。
 * 注意: 只有 preHandle 返回TRUE时后面的方法才会执行，当了拦截器链内存在多个拦截器时，postHandle 只有在拦截器链内的
 * 所有的拦截器返回成功时才会调用，而 afterCompletion 只有 preHandle 返回 true 才会调用，但若是拦截器链内的第一个拦截器的 preHandle 方法返回false ，则后面的方法就都不会执行
 *
 */

@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("MyInterceptor >>>> preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("MyInterceptor >>>> postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("MyInterceptor >>>> afterCompletion");
    }
}
