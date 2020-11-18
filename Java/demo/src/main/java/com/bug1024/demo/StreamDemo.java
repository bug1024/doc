package com.bug1024.demo;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Stream
 *
 * @author bug1024
 * @date 2020-04-06
 */
public class StreamDemo {

    private static final AtomicInteger counter = new AtomicInteger(2100000000);



    @Data
    public static class Obj {
        private String name;
        private List<String> list;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
    }
}
