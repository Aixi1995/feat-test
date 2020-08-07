package com.example.jdk;

import com.example.entity.UserInfo;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/7/29 13:17
 */
public class Feats4Java8 {

    /**
     * 测试 java8 引入的 Optional <br>
     * 1. 可以在预防空指针的时候使用 <br>
     * 2. orElse() 和 orElseGet()最大区别是 orElse()不管非否非空都会执行 <br>
     * 3. ifPresent()使用的较多
     */
    @Test
    public void testOptional() {
        var optional = Optional.of("str");
        Assert.assertEquals("str", optional.orElse(""));
        optional.ifPresent(o -> Assert.assertEquals("str", o));
        var emptyStr = Optional.ofNullable(null).orElse("");
        Assert.assertEquals("", emptyStr);
        var user = new UserInfo();
        user.setItemId(1L);
        user.setName("aixi");
        user.setAge(25);
        Optional.ofNullable(user).ifPresent(u -> {
            Assert.assertEquals(Optional.of(1L).get(), user.getItemId());
            Assert.assertEquals("aixi", user.getName());
            Assert.assertEquals(Optional.of(25).get(), user.getAge());
        });
        UserInfo userNull = null;
        var _user = Optional.ofNullable(userNull).orElseGet(() -> user);
        Optional.ofNullable(_user).ifPresent(u -> Assert.assertEquals("aixi", user.getName()));
    }

    /**
     * 测试java8 引入的stream的常用方法 <br>
     * 1. map 遍历元素 <br>
     * 2. distinct 对元素进行去重 <br>
     * 3. sorted 排序 <br>
     * 4. filter 对元素进行过滤 配合collect使用更佳 <br>
     * 5. reduce 和py的reduce类似的递归使用 <br>
     */
    @Test
    public void testStream() {
        List<String> list = new ArrayList<>();
        list.add("1");list.add("2");list.add("3");list.add("4");
        list.stream().map(s -> s += " ").forEach(System.out::printf);//map可以遍历原stream的元素操作
        System.out.println();
        list.add("1");
        list.stream().distinct().map(s -> s += " ").forEach(System.out::printf);//distinct 对原stream元素去重
        System.out.println();
        //sort可以对元素进行排序
        list.stream().distinct().sorted(String.CASE_INSENSITIVE_ORDER).map(s -> s += " ").forEach(System.out::printf);
        System.out.println();
        // filter 对元素进行过滤
        list.stream().filter(s -> !"100".equals(s)).map(s -> s += " ").forEach(System.out::printf);
        System.out.println();
        Stream<Integer> stream = Stream.of(1, 2, 4, 5, 6, 3);
        var l = stream.distinct().sorted(Integer::compareTo).collect(Collectors.toList());
        l.forEach(System.out::print);
    }


    /**
     * map可以用于便利元素 <br>
     * flapmap适合合流在遍历 <br>
     */
    @Test
    public void testMapAndFlatMap() {
        var user1 = new UserInfo(1L, "wang1", "wang1@163.com", 27);
        var user2 = new UserInfo(1L, "wang1", "wang1@163.com", 24);
        var user3 = new UserInfo(1L, "wang1", "wang1@163.com", 26);
        var user4 = new UserInfo(1L, "wang1", "wang1@163.com", 23);
        var list = new ArrayList<UserInfo>();
        list.add(user1);list.add(user2);list.add(user3);list.add(user4);
        var sumOfAge = list.stream().mapToInt(UserInfo::getAge).sum();
        Assert.assertEquals(100, sumOfAge);
        var user6 = new UserInfo(1L, "wang1", "wang1@163.com", 27);
        var user7 = new UserInfo(1L, "wang1", "wang1@163.com", 24);
        var user8 = new UserInfo(1L, "wang1", "wang1@163.com", 26);
        var user9 = new UserInfo(1L, "wang1", "wang1@163.com", 23);
        var _list = new ArrayList<UserInfo>();
        list.add(user6);list.add(user7);list.add(user8);list.add(user9);
        var sumOfAllAge = Stream.of(list, _list).flatMap(Collection::stream).mapToInt(UserInfo::getAge).sum();
        Assert.assertEquals(200, sumOfAllAge);
    }

    /**
     * 测试 CompletableFuture
     */
    @Test
    public void testCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("thread name of supplyAsync is : " + Thread.currentThread().getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("exception occur while supplyAsync");
                e.printStackTrace();
            }
            return "test CompletableFuture";
        }).handleAsync((t,e) ->{
            try {
                System.out.println("thread name of handle is : " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if (t.equals("test CompletableFuture")) {
                t += " and some other api";
            }
            if (e != null) {
                System.out.println("exception occur while handle");
            }
            return t;
        });
        System.out.println(future.get());
    }

    @Test
    public void testCompletableFuture2Concurr() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> getStr = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "1";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "0";
        });
        CompletableFuture<Integer> getInt = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        });
        CompletableFuture<Float> getDouble = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return 1.0f;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0f;
        });
        System.out.println(getInt.get());//get是阻塞的
        System.out.println(getDouble.get());
        System.out.println(getStr.get());
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }
    /**
     * 测试 LocalDate，在计算日期差的时候特别有用
     */
    @Test
    public void testLocalDate() {
        var date = LocalDate.of(2020, 7, 28);
        System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
        var now = LocalDate.now();
        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        var currMonth = now.getMonth();
        var currDay = now.getDayOfMonth();
        var currDayOfYesr = now.get(ChronoField.DAY_OF_YEAR);//用枚举的方法也可以获取
        System.out.println("当前月份:  " + currMonth + ", 当前月的天数：" + currDay + ", 当前年的天：" + currDayOfYesr);
        var day = now.toEpochDay() - date.toEpochDay();
        System.out.println(day);//计算天数之差
        var day1 = now.until(date, ChronoUnit.DAYS);
        System.out.println(day1);
    }


    /**
     * 测试 LocalTime
     */
    @Test
    public void testLocalTime() {
        var now = LocalTime.now();
        System.out.println(now.format(DateTimeFormatter.ISO_TIME));
    }


    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 测试 LocalDateTime
     */
    @Test
    public void testLocalDateTime() {
        var now = LocalDateTime.now();
        System.out.println(now.format(DATE_TIME_FORMATTER));
        var tomorrow = now.plusDays(3);
        System.out.println(tomorrow.format(DATE_TIME_FORMATTER));
        Assert.assertTrue(tomorrow.isAfter(now));//now.isAfter() now.isEqual()
        Predicate<LocalDateTime> p = date -> date.isAfter(LocalDateTime.now());
        Assert.assertTrue(p.test(tomorrow));
        Function<LocalDateTime, Integer> f = LocalDateTime::getNano;
        System.out.println(f.apply(tomorrow));
    }

    @Test
    public void testFunction() {
        System.out.println(generateName("wang_zhi_qiang", NAME_TO_CAMEL));
        System.out.println(generateName("wangZhiQiang", NAME_TO_UNDERSCORE));
        System.out.println(generateName("wangZhiQiang", String::toUpperCase));
        System.out.println(generateName("wangZhiQiang", String::toLowerCase));
    }

    Function<String, String> NAME_TO_CAMEL = name -> {
        var strs = name.split("_");
        var sb = new StringBuilder(strs[0]);
        for (int i = 1; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            cs[0] -= 32;
            strs[i] = String.valueOf(cs);
            sb.append(strs[i]);
        }
        return String.valueOf(sb);
    };

    Function<String, String> NAME_TO_UNDERSCORE = name -> {
        var sb = new StringBuilder();
        Arrays.stream(name.split("")).forEach(s -> {
            if (s.matches("[A-Z]")) {
                sb.append("_");
            }
            sb.append(s);
        });
        return String.valueOf(sb).toLowerCase();
    };

    public String generateName(String name, Function<String, String> f) {
        return f.apply(name);
    }

}
