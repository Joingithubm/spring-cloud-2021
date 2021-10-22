package com.athome.redis.main;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/19 14:57
 * @Version 1.0
 */
public class JDKProxy {

    public static void main(String[] args) {


    }
}


class UserHandler implements InvocationHandler{

    private Object object;
    public UserHandler(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法之前--");
        Object invoke = method.invoke(object, args);
        System.out.println("方法之后--");
        return invoke;
    }
}