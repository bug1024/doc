package com.bug1024.demo;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author bug1024
 * @date 2019-08-04
 */
@SpringBootApplication
@RestController
@Slf4j
@EnableAsync
@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Resource
    private ApplicationContext applicationContext;

    @Bean
    public Object a() {
        System.out.println("a");
        return new Object();
    }


    @Bean
    @DependsOn("c")
    public Object b() {
        System.out.println("b");
        return new Object();
    }

    @Bean
    public Object c() {
        System.out.println("c");
        return new Object();
    }

    @Component
    public static class Demo1 {
        @Bean
        public Object e() {
            System.out.println("e");
            return new Object();
        }
    }

    @Configuration
    public static class Demo {
        @Bean
        public Object d() {
            System.out.println("d");
            return new Object();
        }
    }

    @PostMapping("/test")
    public Object test(@RequestBody @Validated({RequestDTO.Test.class}) RequestDTO request) {
        return request;
    }

    @PostMapping("/test2")
    public Object test2(@RequestBody @Validated RequestDTO request) {
        return request;
    }

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping("/test3")
    public Object test3() {
        long start = System.currentTimeMillis();


        List<CompletableFuture<String>> futures = new ArrayList<>();
        futures.add(CompletableFuture.supplyAsync(this::mock1, threadPoolTaskExecutor));
        futures.add(CompletableFuture.supplyAsync(this::mock2, threadPoolTaskExecutor));
        futures.add(CompletableFuture.supplyAsync(this::mock3, threadPoolTaskExecutor));

        CompletableFuture<List<String>> all = allOf(futures);
        List<String> list = all.join();

        long total = System.currentTimeMillis() - start;
        Map<String, Object> maps = new HashMap<>();
        maps.put("total", total);
        maps.put("list", list);
        return maps;
    }

    public String mock1() {
        System.out.println("mock1");
        sleep(1000);
        return "1";
    }
    public String mock2() {
        System.out.println("mock2");

        sleep(1000);

        return "1";
    }
    public String mock3() {
        System.out.println("mock3");

        sleep(1000);

        return "1";
    }

    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (Exception e) {

        }
    }

    public static <T> CompletableFuture<List<T>> allOf(Collection<CompletableFuture<T>> futures) {
        return futures.stream().collect(Collectors.collectingAndThen(
                Collectors.toList(),
                l -> CompletableFuture.allOf(l.toArray(new CompletableFuture[0]))
                        .thenApply(v -> l.stream().map(CompletableFuture::join).collect(Collectors.toList()))
                )
        );
    }

    private String getData(QueryDTO query) {
        sleep(400);
        String a = String.valueOf(query.getPageNum());
        log.info("data:{}", a);
        return a;
    }

    @Data
    public static class QueryDTO {
        private Integer pageNum;
    }

    @GetMapping("/test4")
    public Object test4() {
        long start = System.currentTimeMillis();
        List<CompletableFuture<String>> futures = new ArrayList<>();
        int total = 20;
        QueryDTO query = new QueryDTO();
        for (int i = 1; i <= total; i++) {
            QueryDTO newQuery = new QueryDTO();
            BeanUtils.copyProperties(newQuery, query);
            newQuery.setPageNum(i);
                CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> getData(newQuery), threadPoolTaskExecutor);
                futures.add(task);
        }
        futures.forEach(task -> {
            try {
                task.get(1000, TimeUnit.MILLISECONDS);
            } catch (Exception e) {

            }
        });
        return System.currentTimeMillis() - start;
    }

    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("excel-download-");
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(50);
        // (coreSize / taskCost) * responseTime
        // 30 / 0.4 * 3
        executor.setQueueCapacity(200);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();

        return executor;
    }

    @Scheduled(fixedRate = 5000)
    public void schedule() {
        log.info("active:{} poolSize:{}", threadPoolTaskExecutor.getActiveCount(), threadPoolTaskExecutor.getPoolSize());
    }


    @GetMapping("export")
    public Object export(
            @RequestParam(name = "total", defaultValue = "500") Integer total,
            @RequestParam(name = "page", defaultValue = "20") Integer page
    ) throws Exception {
        String fileName = "test";
        File excel = File.createTempFile(fileName, ExcelTypeEnum.XLSX.getValue());
        ExcelWriter excelWriter = EasyExcelFactory.write(excel, TradeSaleOrderExcelData.class).build();
        WriteSheet writeSheet = EasyExcelFactory.writerSheet("sheet1").build();
        long start = System.currentTimeMillis();


        Map<Integer, Long> times = Maps.newHashMap();

        for (int i = 0; i < page; i++) {
            long t1 = System.currentTimeMillis();
            excelWriter.write(data(total), writeSheet);
            long t = System.currentTimeMillis() - t1;
            times.put(i, t);
        }
        excelWriter.finish();
        Map<String, Object> map = Maps.newHashMap();
        map.put("file", excel.getAbsolutePath());
        map.put("count", total * page);
        map.put("cost", System.currentTimeMillis() - start);
        map.put("times", times);
        return map;
    }


    private List<TradeSaleOrderExcelData> data(int total) {
        TradeSaleOrderExcelData data =  new TradeSaleOrderExcelData();
        data.setProjectOwnerCityName("北京");
        data.setCompanyName("北京xxxxxx公司");
        data.setProjectSourceText("xxxx");
        data.setCooperationModeText("xxxx");
        data.setProjectName("xxxx");
        data.setPropertyTypeName("xxxx");
        data.setHouseId("111111111111111111111111");
        data.setHouseName("22222222222222");
        data.setCustomerName("3333333333");
        data.setPurchaserName("xxxxx");
        data.setCustomerSource("xxxxx");
        data.setTradeTypeText("xxxxx");
        data.setTradeStatusText("xxxx");
        data.setSignTime(new Date());
        data.setCloseTime(new Date());
        data.setCloseReasonText("xxxx");
        data.setCreateTime(new Date());
        data.setPropertyConsultantName("xxxx");
        data.setContractTotalPrice(new BigDecimal("0"));
        data.setTotalIncomes(new BigDecimal("0"));
        data.setDevelop1("xxxx");
        data.setBaseAgencyFeeRate1(new BigDecimal("0"));
        data.setBaseAgencyFeeBonusRate1(new BigDecimal("0"));
        data.setOverPriceRate1(new BigDecimal("0"));
        data.setMarketPromotionFeeRate1(new BigDecimal("0"));
        data.setOtherIncomeFeeRate1(new BigDecimal("0"));
        data.setAccrualIncome1(new BigDecimal("0"));
        data.setDevelop2("bbbb");
        data.setBaseAgencyFeeRate2(new BigDecimal("0"));
        data.setBaseAgencyFeeBonusRate2(new BigDecimal("0"));
        data.setOverPriceRate2(new BigDecimal("0"));
        data.setMarketPromotionFeeRate2(new BigDecimal("0"));
        data.setOtherIncomeFeeRate2(new BigDecimal("0"));
        data.setAccrualIncome2(new BigDecimal("0"));
        data.setDevelop3("ccccc");
        data.setBaseAgencyFeeRate3(new BigDecimal("0"));
        data.setBaseAgencyFeeBonusRate3(new BigDecimal("0"));
        data.setOverPriceRate3(new BigDecimal("0"));
        data.setMarketPromotionFeeRate3(new BigDecimal("0"));
        data.setOtherIncomeFeeRate3(new BigDecimal("0"));
        data.setAccrualIncome3(new BigDecimal("0"));
        data.setDevelop4("");
        data.setBaseAgencyFeeRate4(new BigDecimal("0"));
        data.setBaseAgencyFeeBonusRate4(new BigDecimal("0"));
        data.setOverPriceRate4(new BigDecimal("0"));
        data.setMarketPromotionFeeRate4(new BigDecimal("0"));
        data.setOtherIncomeFeeRate4(new BigDecimal("0"));
        data.setAccrualIncome4(new BigDecimal("0"));

        List<TradeSaleOrderExcelData> list = Lists.newArrayListWithExpectedSize(total);
        for (int i = 0; i < total; i++) {
            list.add(data);
        }

        return list;
    }
}