package com.bug1024.demo;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangyu
 * @date 2020-05-20
 */
public class JolDemo {
    public static void main(String[] args) {
        Boolean a = true;
        ClassLayout layout = ClassLayout.parseInstance(a);
        System.out.println(layout.toPrintable());

        boolean a2 = true;
        ClassLayout layout2 = ClassLayout.parseInstance(a2);
        System.out.println(layout2.toPrintable());
    }
}
