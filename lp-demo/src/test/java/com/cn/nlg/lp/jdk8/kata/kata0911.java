package com.cn.nlg.lp.jdk8.kata;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static java.lang.System.out;

/**
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/11 </pre>
 */
@Slf4j
public class kata0911 {


    public static void main(String[] args) {
        Kata0911Demo kata = new Kata0911Demo();
        Map map = kata.q1("1234567891234asfasdf");
        log.info(map.toString());
        //linkedList 是怎么做的呢，是一个链表，如果大于一半 则从最后一个元素往前遍历
        // 如果index 小于一半 从第一个元素往后遍历。。
        //总而言之 迭代就对了
        kata.q2(2, 5);
//        List list = new LinkedList<>();
//        list.add("aa");
//        list.remove("aa");
//        System.out.println(11>>1);
//        Object obj []={};
//        Arrays.copyOf(obj, 112);

    }


}

@NoArgsConstructor
class Kata0911Demo {

    /**
     * 多线程 交替运行两线程 运行5次
     */
    private int status = 1;

    /*
   测试字符串中字符出现的频率 并且输出 出现次数为偶数的 字符 以及次数
    */
    Map<String, Integer> q1(String target) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (char c : target.toCharArray()) {
            map.merge("" + c, 1, (x, y) -> x + y);
        }
        for (char c : target.toCharArray()) {
            if (map.get("" + c) % 2 != 0) map.remove("" + c);
        }
        return map;
    }

    void q2(int num, int times) {
        ReentrantLock lock = new ReentrantLock();
        Condition cd1 = lock.newCondition();
        Condition cd2 = lock.newCondition();


        Runnable r1 = () -> {
            lock.lock();
            if (status != 1) try {
                cd1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < num; i++) {
                out.print("A");
            }
            cd2.signal();
            lock.unlock();
            status = 2;
        };

        Runnable r2 = () -> {
            lock.lock();
            if (status != 2) try {
                cd2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < num; i++) {
                out.print("B");
            }
            cd1.signal();
            lock.unlock();
            status = 1;
        };
        for (int i = 0; i < times; i++) {
            new Thread(r1).start();
            new Thread(r2).start();
        }


    }

    void q3() {

        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map1 = new HashMap<>();
        //把获取的参数 赋给map       ---->消费者
        BiConsumer<String, Integer> biC = map::put;
        //把获取的参数相加 并返回结果 ---->生产者
        BiFunction<Integer, Integer, Integer> biF = (x, y) -> x + y;
        map1.put("key", 1);
        map1.replace("key", 11);
        System.out.println("map1:" + map1.get("key"));
        map.computeIfAbsent("hah", (k) -> {
                    System.out.println("map: " + k);
                    return 12;
                }
        );
        //getOrDefault 不操作map 只返回值
        System.out.println("getOrDefault:" + map.getOrDefault("haha", 16));
        //computeIfABsent 和compute 的区别是 前一个 第二个参数为 一元函数，第二个为二元函数
        //此时返回的值是和直接get返回的值一样的。Hashget 覆盖了默认接口方法。此时只返回值不做任何操作
        System.out.println("map1: " + map.replace("hah", 1116));

        //此方法在Hashmap中被覆盖  最后调用了afterNodeAccess(){}方法 该方法在Hashmap中没有被重写 所以不会新增值
        System.out.println(map.putIfAbsent("key", 19));
        System.out.println(1 << 30);

        map.keySet();


//        Supplier Product Consumer
    }


}

