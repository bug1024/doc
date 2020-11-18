package com.bug1024.demo;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * JMH 进行基准测试的使用过程并不复杂，同为 Java 虚拟机团队开发，准确性毋容置疑。
 * 但是在进行基准测试时还是要注意自己的代码问题，如果编写的要进行测试的代码本身存在问题，那么测试的结果必定是不准的
 *
 * @author wangyu
 * @date 2018-02-26
 */
// 统计平均响应时间
@BenchmarkMode(Mode.AverageTime)
// 每个进行基准测试的线程都会独享一个对象示例
@State(Scope.Thread)
// 开启一个线程进行测试
@Fork(1)
// 输出的时间单位
@OutputTimeUnit(TimeUnit.MILLISECONDS)
// 微基准测试前进行三次预热执行
@Warmup(iterations = 3)
// 进行 5 次微基准测试
@Measurement(iterations = 5)
public class JmhDemo {

    String string = "";
    StringBuilder stringBuilder = new StringBuilder();

    //@Benchmark
    public String stringAdd() {
        for (int i = 0; i < 1000; i++) {
            string = string + i;
        }
        return string;
    }

    //@Benchmark
    public String stringBuilderAppend() {
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }

    @Benchmark
    public void pattern() {
        String eL = "(([1-9]{1}\\d*)|(0{1}))(\\.\\d{0,2})";
        Pattern a = Pattern.compile(eL);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhDemo.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
