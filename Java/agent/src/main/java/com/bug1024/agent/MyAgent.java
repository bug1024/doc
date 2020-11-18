package com.bug1024.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 * MyAgent
 * @author wangyu
 * @date 2020-07-18
 */
public class MyAgent {

    private static final Logger logger = LoggerFactory.getLogger(MyAgent.class);

    /**
     * jvm 参数形式启动，运行此方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        logger.info("agentArgs:{}", agentArgs);
        logger.info("premain is running");
        // 添加一个类转换器
        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            // JVM加载的所有类会流经这个类转换器
            logger.info("class loaded:{}", className);
            // 直接返回原本的字节码
            return classfileBuffer;
        });
    }

    /**
     * 动态 attach 方式启动，运行此方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        logger.info("agentArgs:{}", agentArgs);
        for (; ; ) {
            logger.warn("agentmain is running");
            try {
                Thread.sleep(2000);
            } catch (Exception ignore) {

            }
        }
    }

}
