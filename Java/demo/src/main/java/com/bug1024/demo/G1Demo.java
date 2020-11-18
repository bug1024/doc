package com.bug1024.demo;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * G1
 * @author bug1024
 * @date 2020-04-11
 */
public class G1Demo {

    /**
     * -verbose:gc -Xms10m -Xmx10m -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:MaxGCPauseMillis=200m
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        System.out.println(decode("BXF9K5ZYM3"));
        System.out.println(decode("BXF9IPK34L"));

        int size = 1024 * 1024;
        byte[] a = new byte[size];
        byte[] b = new byte[size];
        byte[] c = new byte[size];
        byte[] d = new byte[size];

        System.out.println("hello");
        Thread.sleep(1000000);
    }


    private static double computeDuration(double duration) {
        if (duration < 20) {
            return duration;
        } else if (duration < 200) {
            return duration - duration % 5;
        } else if (duration < 2000) {
            return duration - duration % 50;
        } else {
            return duration - duration % 500;
        }
    }

    static final int RADIX = 36;
    static final Long SEED = 3141592L;



    public static String encode(Long id) {
        Long xor = id ^ SEED;
        BigInteger value = new BigInteger(xor.toString());
        return value.toString(RADIX).toUpperCase();
    }

    public static Long decode(String strId) {
        String tempStrId = strId.trim();
        if (strId.isEmpty()) {
            return null;
        }
        BigInteger value = new BigInteger(tempStrId.toLowerCase(), RADIX);
        return value.longValue() ^ SEED;
    }

    public static List<Long> decode(List<String> strIds) {
        List<Long> rs = Lists.newArrayList();
        for (String strId : strIds) {
            rs.add(decode(strId));
        }
        return rs;
    }
}
