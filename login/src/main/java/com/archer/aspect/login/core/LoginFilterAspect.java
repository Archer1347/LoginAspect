package com.archer.aspect.login.core;

import android.content.Context;
import android.util.Log;

import com.archer.aspect.login.annotation.LoginFailedFilter;
import com.archer.aspect.login.annotation.LoginFilter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 登录过滤切面
 * Create by linjiaqiang 5/5/21
 */
@Aspect
public class LoginFilterAspect {
    private static final String TAG = "LoginFilterAspect";

    @Pointcut("execution(@com.archer.aspect.login.annotation.LoginFilter * *(..))")
    public void loginFilter() {
    }

    @Around("loginFilter()")
    public void aroundLoginPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        final Object object = joinPoint.getThis();
        if (object == null) {
            return;
        }
        ILogin iLogin = LoginAssistant.getInstance().getLogin();
        if (iLogin == null) {
            throw new IllegalAccessException("LoginSDK 没有初始化！");
        }

        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalAccessException("LoginFilter 注解只能用于方法上");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        LoginFilter loginFilter = methodSignature.getMethod().getAnnotation(LoginFilter.class);
        if (loginFilter == null) {
            return;
        }

        Context param = LoginAssistant.getInstance().getApplicationContext();

        if (iLogin.isLogin(param)) {
            try {
                joinPoint.proceed();
            } catch (Exception ex) {
                Log.e("LoginFilterAspect", "aroundLoginPoint(LoginFilterAspect.java:58)-->>" + ex.getLocalizedMessage());
            }
        } else {
            iLogin.login(param, loginFilter.userDefine());
            noLogin(object);
        }
    }

    public void noLogin(Object object) {
        Class<?> cls = object.getClass();
        Method[] methods = cls.getDeclaredMethods();
        if (methods.length == 0) {
            return;
        }
        for (Method method : methods) {
            //过滤不含自定义注解PermissionDenied的方法
            boolean isHasAnnotation = method.isAnnotationPresent(LoginFailedFilter.class);
            if (isHasAnnotation) {
                method.setAccessible(true);
                //获取方法上的注解
                LoginFailedFilter aInfo = method.getAnnotation(LoginFailedFilter.class);
                if (aInfo == null) {
                    return;
                }

                try {
                    method.invoke(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}