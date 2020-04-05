package com.bug1024.demo;

/**
 * @author wangyu
 * @date 2019-12-28
 */
public class DeadLockDemo {

    private static Object A = 'a';
    private static Object B = "b";

    public static void main(String[] args) throws Exception {
        Thread a = new Thread(() -> {
           synchronized (A) {
               try {
                   Thread.sleep(1000000000);
               } catch (Exception e) {

               }
               System.out.println("a");
               synchronized (B) {
                   System.out.println("b");
               }
           }
        });
        Thread b = new Thread(() -> {
            synchronized (B) {
                System.out.println("bb");
                synchronized (A) {
                    System.out.println("aa");
                }
            }
        });
        a.start();
        a.join();
        b.start();
    }
}
