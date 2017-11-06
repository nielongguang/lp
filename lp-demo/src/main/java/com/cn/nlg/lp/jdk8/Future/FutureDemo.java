package com.cn.nlg.lp.jdk8.Future;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ${entry.className} Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/5 </pre>
 */
@Slf4j
public class FutureDemo {

    Runnable runnable1 = () -> System.out.println("runnable: " + Thread.currentThread().getName());
    Callable<String> callable = () -> {
        System.out.println("callable: " + Thread.currentThread().getName());
        return "something";
    };

    public void execute(Runnable runnable) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            es.submit(runnable);
            System.out.println("i:" + i + "  " + Thread.currentThread().getName());
        }
    }

    public void excute(Callable<String> callable) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            es.submit(callable);
        }
    }

    public List<Future<String>> excute() {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<String>> list = new ArrayList<Future<String>>(128);
        for (int i = 0; i < 5; i++) {
            list.add(es.submit(this.callable));
        }
        return list;
    }

    public void execute(List<Future<String>> list) {
        for (Future<String> future : list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                log.info("异常中断");
            } catch (ExecutionException e) {
                log.info("执行时异常");
            }
        }

    }


}
