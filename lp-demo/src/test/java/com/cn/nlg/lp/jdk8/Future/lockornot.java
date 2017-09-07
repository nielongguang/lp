package com.cn.nlg.lp.jdk8.Future;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ${entry.className} Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/5 </pre>
 */
@Slf4j
public class lockornot {

    private ExecutorService es = Executors.newCachedThreadPool();

    @Test
    public void TestUnsate() {
        List<Future> list = new ArrayList<>();
        final Outputter output = new Outputter();
//线程安全
//        Runnable runnable1=() -> output.output("zhangsan");
//        Runnable runnable2=() -> output.output("lisi");
//        list.add(es.submit(runnable1));
//        log.info("r1>>>end");
//        list.add(es.submit(runnable2));
//        log.info("r2>>>end");
//        //线程安全
//        Runnable runnable3=() -> output.output1("zhangsan");
//        Runnable runnable4=() -> output.output1("lisi");
//        list.add(es.submit(runnable3));
//        log.info("r3>>>end");
//        list.add(es.submit(runnable4));
        //线程不安全
        log.info("r4>>>end");
        Runnable runnable5 = () -> output.output2("zhangsan");
        Runnable runnable6 = () -> output.output2("lisi");
//        list.add(es.submit(runnable5));
//        log.info("r5>>>end");
//        list.add(es.submit(runnable6));
//        log.info("r6>>>end");

//        Runnable runnable7 = () -> output.output3("zhangsan");
//        Runnable runnable8 = () -> output.output3("lisi");
//        list.add(es.submit(runnable7));
//        log.info("r7>>>end");
//        list.add(es.submit(runnable8));
//        log.info("r8>>>end");
        for (Future future : list) {
            try {
                future.get();
            } catch (ExecutionException e) {
                log.info("执行异常");
            } catch (InterruptedException e) {
                log.info("异常中断异常");
            }
        }


    }


}


@Slf4j
class Outputter {

    /**
     * 线程安全的类 用锁实现~ 最优雅的实现方式哦 如果竞争高的话 性能优于Synchronized
     */
    private Lock lock = new ReentrantLock();// 可重复使用锁对象

    /**
     * 线程安全的类,同步代码块
     */
    void output(String name) {
        synchronized (this) {
            //   为了保证对name的输出不是一个原子操作，这里逐个输出name的每个字符
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.info("异常中断" + Thread.currentThread().getName());

                }
            }
            System.out.println();
        }
    }

    /**
     * 线程安全的类,同步方法
     */
    synchronized void output1(String name) {
        for (int i = 0; i < name.length(); i++) {
            System.out.print(name.charAt(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.info("异常中断");
            }
        }
        System.out.println();
    }


    /**
     * 线程不安全的类
     */
    void output2(String name) {

        for (int i = 0; i < name.length(); i++) {
            System.out.print(name.charAt(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.info("异常中断");
            }
        }

        System.out.println();
    }

    public void output3(String name) {
        // 线程输出方法
        lock.lock();// 得到锁
        try {
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
        } finally {
            System.out.println();
            lock.unlock();// 释放锁
        }
    }

}








