package com.bug1024.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * MyMain
 * @author wangyu
 * @date 2020-07-18
 */
public class AgentDemo {

    private static final Logger logger = LoggerFactory.getLogger(AgentDemo.class);

    /**
     * -javaagent:/Users/bug1024/Github/doc/Java/agent/target/agent-1.0-SNAPSHOT.jar
     */
    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            int x = new Random().nextInt();
            new AgentDemo().test(x);
        }
    }

    public void test(int x) throws InterruptedException {
        Thread.sleep(2000);
        logger.info("app is running {}", x);
    }
}
