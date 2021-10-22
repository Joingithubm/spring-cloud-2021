package com.athome.spring5.aspect;

import com.athome.spring5.base.LogRecord;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.CollectionUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/19 17:06
 * @Version 1.0
 */
@Aspect
public class LogAspect {

    //@Pointcut("@annotation(com.athome.spring5.base.LogRecord)")
    @Pointcut("execution(*  com.athome.spring5..*.*(..))")
    public void cut(){};

    @Before("cut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before 方法执行前");
    }

    @After("cut()")
    public void after(JoinPoint joinPoint){
        System.out.println("After 方法后置通知执行");
    }

    @AfterReturning("cut()")
    public void afterReturn(JoinPoint joinPoint){
        System.out.println("afterReturun 返回通知");
    }

    @AfterThrowing("cut()")
    public void afterThrowing(){
        System.out.println("afterThrowing  异常通知");
    }
    @Around("cut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {


        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
//        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
//        System.out.println(Arrays.toString(declaredAnnotations));
//        List<Annotation> collect = Arrays.stream(declaredAnnotations).filter(u ->{
//            System.out.println(u.annotationType().getName());
//           return u instanceof LogRecord;
//        } ).collect(Collectors.toList());
//        if (CollectionUtils.isEmpty(collect)){
//            return joinPoint.proceed();
//        }
        System.out.println("@Around 方法执行之前");
        Object proceed = joinPoint.proceed();
        System.out.println("@Around 方法之后");
        return proceed;
    }


}
