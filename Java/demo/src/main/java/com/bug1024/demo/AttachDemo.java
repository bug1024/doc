package com.bug1024.demo;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * MyMain
 * @author wangyu
 * @date 2020-07-18
 */
public class AttachDemo {

    private static final Logger logger = LoggerFactory.getLogger(AttachDemo.class);

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        logger.info("AttachDemo begin");
        // 待绑定的jvm pid
        VirtualMachine vm = VirtualMachine.attach("8522");
        vm.loadAgent("/Users/bug1024/Github/doc/Java/agent/target/agent-1.0-SNAPSHOT.jar");
        System.in.read();
    }
}
