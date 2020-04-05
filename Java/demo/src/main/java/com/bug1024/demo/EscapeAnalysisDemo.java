package com.bug1024.demo;

/**
 * @author bug1024
 * @date 2020-03-20
 */
public class EscapeAnalysisDemo {

    /**
     * 开启逃逸分析&标量替换
     * -Xmx4G -Xms4G -XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        long a1 = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i++) {
            alloc();
        }
        // 查看执行时间
        long a2 = System.currentTimeMillis();
        System.out.println("cost " + (a2 - a1) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();
    }

    static class User {

    }
}
