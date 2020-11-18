package com.bug1024.demo;

/**
 * code style
 * @author bug1024
 * @date 2020-05-17
 */
public class CodeStyleDemo {
    public static void main(String[] args) {

        boolean a = false;
        boolean b = true;

        if (Boolean.FALSE.equals(a)) {
            System.out.println("1111111111111111");
        }
        if (Boolean.TRUE.equals(b)) {
            System.out.println("2222222");
        }

        boolean flag = true;
        boolean simpleBoolean = false;
        Boolean nullBoolean = null;
        boolean x = flag ? nullBoolean : simpleBoolean;
        System.out.println(x);
    }
}
