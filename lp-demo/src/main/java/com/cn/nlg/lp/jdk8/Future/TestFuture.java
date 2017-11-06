package com.cn.nlg.lp.jdk8.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/26 </pre>
 */
public class TestFuture {

    Callable<String> callable = () -> {
        System.out.println("callable: " + Thread.currentThread().getName());
        return "something";
    };

    ExecutorService es = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {

    }


}
