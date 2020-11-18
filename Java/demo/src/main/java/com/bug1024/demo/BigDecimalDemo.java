package com.bug1024.demo;

import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 浮点数
 * @author bug1024
 * @date 2020-04-18
 */
public class BigDecimalDemo {

    /**
     * 1.如果你希望BigDecimal能够精确地表示你希望的数值，那么一定要使用字符串来表示小数，并传递给BigDecimal的构造函数。
     * 2.如果你使用Double.toString来把double转化字符串，然后调用BigDecimal(String)，这个也是不靠谱的，它不一定按你的想法工作。
     * 3.如果你不是很在乎是否完全精确地表示，并且使用了BigDecimal(double)，那么要注意double本身的特例，double的规范本身定义了几个特殊的double值(Infinite，-Infinite，NaN)，不要把这些值传给BigDecimal，否则会抛出异常。
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> projects = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            projects.add(i + "");
        }

        List<List<String>> summaryList = projects.parallelStream().map(project -> {
            List<List<String>> lists = data();
            return lists;
        }).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());

        Long b = 0L;
        if (b == 0L) {
            System.out.println("xxxxxxxxxxxx");
            return;
        }
        byte a = 1;
        byte aa = 0;
        byte bb = '0';
        byte bbb = 'a';
        // 0.30000000000000004
        System.out.println(0.1 + 0.2);

        // 0.1000000000000000055511151231257827021181583404541015625
        System.out.println(new BigDecimal(0.1).toString());

        // 0.200000000000000011102230246251565404236316680908203125
        System.out.println(new BigDecimal(0.2).toString());

        // 0.3999999999999999944488848768742172978818416595458984375
        System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.3)));

        // 0.3
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));

        // 0.3
        System.out.println(BigDecimal.valueOf(0.1).add(BigDecimal.valueOf(0.2)));

        // 0.1
        System.out.println(new BigDecimal("0.1").toString());

        // 0.1
        System.out.println(new BigDecimal(0.1).doubleValue());

        // 0.1
        System.out.println(new BigDecimal(Double.toString(0.1000000000000000055511151231257827021181583404541015625)).toString());
        // 0.1
        System.out.println(new BigDecimal(Double.toString(0.1)).toString());

        // 0.1
        System.out.println(new BigDecimal(Double.toString(0.1000000000000000055511151231257827021181583404541015625)).toString());

        System.out.println(new BigDecimal(Double.toString(0.100000010000000005)).toString());

    }


    public static List<List<String>> data() {
        List<List<String>> result = Lists.newArrayList();
        List<String> e = Lists.newArrayList("1");
        result.add(e);
        return result;
    }
}