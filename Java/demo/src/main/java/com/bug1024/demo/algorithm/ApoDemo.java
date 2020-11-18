package com.bug1024.demo.algorithm;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.lang.reflect.Method;

/**
 * CGlib可以传入接口也可以传入普通的类，接口使用实现的方式,普通类使用会使用继承的方式生成代理类.
 * 由于是继承方式,如果是 static方法,private方法,final方法等描述的方法是不能被代理的
 * 做了方法访问优化，使用建立方法索引的方式避免了传统JDK动态代理需要通过Method方法反射调用.
 * 提供callback 和filter设计，可以灵活地给不同的方法绑定不同的callback。编码更方便灵活。
 * CGLIB会默认代理Object中equals,toString,hashCode,clone等方法。比JDK代理多了clone。
 *
 * @author bug1024
 * @date 2020-09-01
 */
@Slf4j
public class ApoDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new MyInterceptor());
        enhancer.setSuperclass(ApoDemo.class);
        Object proxy =  enhancer.create();




//        Objenesis objenesis = new ObjenesisStd(true);
//        ApoDemo proxy2 = (ApoDemo) objenesis.newInstance(enhancer.createClass());
//        proxy2.say1();
//        proxy2.say2();
    }

    public void say1() {
        System.out.println("public");
    }

    private void say2() {
        System.out.println("private");
    }


    public static class MyInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before: " + method.getName());
            methodProxy.invokeSuper(o, objects);
            System.out.println("after: " + method.getName());
            return null;
        }
    }
}
