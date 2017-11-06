package com.cn.nlg.lp.jdk8.kata;


import lombok.NonNull;
import org.junit.Assert;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

/**
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/8 </pre>
 */

public class kata0908 {

    private ReentrantLock rlock = new ReentrantLock();
    private Condition condition1 = rlock.newCondition();
    private Condition condition2 = rlock.newCondition();
    private int state = 1;

    private String q1(@NonNull String s1, @NonNull String s2) {

        Map<String, Integer> map = new TreeMap<>();
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        Integer count = 0;
        for (char c : s1.toCharArray()) {
            for (char c1 : s2.toCharArray()) {
                if (c == c1) {
                    map.merge("" + c, count, (x, y) -> x);
                }
            }
            count++;
        }
        StringBuilder sb = new StringBuilder();
        Map<Integer, String> map1 = new TreeMap<>();
        map.forEach((s, i) -> map1.put(i, s));
        map1.forEach((integer, s) -> sb.append(s));
        return sb.toString();
    }

    @Test
    public void testQ1() {
        String result = this.q1("1,35", "2,415");
        String result1 = this.q1("1,3,5,4", "2,4,1,7,5");
        Assert.assertEquals("1,5", result);
        Assert.assertEquals("1,54", result1);
    }

    private Integer q2(String s) {
        s = s.replace("x", "*");
        ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            return Integer.parseInt(se.eval(s).toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    @Test
    public void testQ2() {
        int result = this.q2("(4+8)x2/(3-2)");
        int result1 = this.q2("33*42");
        out.println(result1);
        Assert.assertEquals(24, result);


// ScriptEngineManager manager = new ScriptEngineManager();
        // 得到所有的脚本引擎工厂
//    List<ScriptEngineFactory> factories = manager.getEngineFactories();
        // 这是Java SE 5 和Java SE 6的新For语句语法
//    for (ScriptEngineFactory factory: factories){
//                // 打印脚本信息
//                            out.printf("Name: %s%n" +
//                            "Version: %s%n" +
//                            "Language name: %s%n" +
//                            "Language version: %s%n" +
//                            "Extensions: %s%n" +
//                            "Mime types: %s%n" +
//                            "Names: %s%n",
//                            factory.getEngineName(),
//                            factory.getEngineVersion(),
//                            factory.getLanguageName(),
//                            factory.getLanguageVersion(),
//                            factory.getExtensions(),
//                            factory.getMimeTypes(),
//                            factory.getNames());
//    }

    }

    private Map<String, Integer> q3(String s) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (char c : s.toCharArray()) {
            map.merge("" + c, 1, (x, y) -> x + y);

        }

        map.forEach((s1, integer) -> {
            if (integer % 2 != 0) map.remove(s1);
        });
        return map;
    }

    @Test
    public void testQ3() {
        System.out.println(this.q3("sheepmusheepmummm1").toString());
    }

    String q4(int num, int times) {
        ExecutorService es = Executors.newCachedThreadPool();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < times; j++) {
            es.execute(() -> {
                rlock.lock();
                while (state != 1) {
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < num; i++) {
                    System.out.print("A");
                    sb.append("A");
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                state = 2;
                condition2.signal();
                rlock.unlock();
            });

            es.execute(() -> {
                rlock.lock();
                while (state != 2) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < num; i++) {
                    System.out.print("B");
                    sb.append("B");
                }

                state = 1;
                condition1.signal();
                rlock.unlock();
            });
        }
        System.out.println(es.isShutdown());
        es.shutdown();
        return sb.toString();
    }


}


