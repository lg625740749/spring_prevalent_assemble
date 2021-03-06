package com.coffeeandice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author: CoffeeAndIce
 * @Todo 用于自定义切面调用
 */

public class DemoAspect {
    //前置通知
    public void before(JoinPoint joinPoint) {
        //获取节点名称
        System.out.println("前置调用方法" );
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());
    }

    //后置通知(一旦抛出异常后就不会被执行)
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("后置返回通知结果" + result);
    }

    //环绕通知（说白了，可以在方法前后设置所需定制）
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知-前");
        //调用目标方法
        Object proceed = joinPoint.proceed();
        System.out.println("环绕通知-后");
        return proceed;
    }

    //异常通知（有一场会被抓到这里）
    public void afterException(JoinPoint joinPoint, Exception exception) {
        System.err.println("在异常之后通知:" + exception);
    }


    // 后置通知 （总会执行,但不能访问到返回值内容)
    public void after(JoinPoint joinPoint) {
        System.out.println("后置通知");
    }

}
