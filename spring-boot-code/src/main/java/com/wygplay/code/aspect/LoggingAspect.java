package com.wygplay.code.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author wyg
 * @version 1.0
 * @date 2021/8/11 20:16
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Spring AOP不对请求参数进行处理时的日志记录方法
     * @param pjb 代理对象
     * @return 返回实例
     * @throws Throwable
     */
    @Around("execution(* com.wygplay..*(..))")
    private Object logProcessing(ProceedingJoinPoint pjb) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjb.getSignature();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = pjb.proceed();
        stopWatch.stop();
        log.info("{}.{} 方法执行完毕，耗时{}秒", methodSignature.getDeclaringTypeName(), methodSignature.getMethod().getName(), stopWatch.getTotalTimeSeconds());
        return result;
    }
}
