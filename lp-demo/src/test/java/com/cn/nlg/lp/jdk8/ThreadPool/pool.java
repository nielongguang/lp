package com.cn.nlg.lp.jdk8.ThreadPool;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ${entry.className} Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/6 </pre>
 */
public class pool {
    //     1.  创建一个可重用固定线程集合的线程池，以共享的无界队列方式来运行这些线程,此线程没有超时时间，线程池不会自动扩张大小
    static ExecutorService threadPoolFixed = Executors.newFixedThreadPool(3);//
    //     2. 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们，过时时间为60秒，但是所有线程都会过期，不会保持
    static ExecutorService threadPoolCached = Executors.newCachedThreadPool();
    //     3. 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
    static ExecutorService threadPoolSingle = Executors.newSingleThreadExecutor();
    //     4. 创建一个可安排在给定延迟后运行命令或者定期地执行的线程池。
    static ScheduledExecutorService threadPoolSchedule = Executors.newScheduledThreadPool(3);//

    Data data = new Data();

    public static void main(String[] args) {

        Callable<String> callable = () -> {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("时间 ：" + LocalDateTime.now().toString() + "次执行" + Thread.currentThread().getName());
            }
            return "hello";
        };
        Runnable runnable = () -> {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("时间：" + LocalDateTime.now().toString() + " 次执行" + Thread.currentThread().getName());

            }
        };

        ScheduledFuture scheduledFuture = null;

        threadPoolFixed.submit(callable);
        threadPoolCached.submit(callable);
        threadPoolSingle.submit(callable);
        threadPoolSchedule.schedule(runnable, 10, TimeUnit.SECONDS);
        scheduledFuture = threadPoolSchedule.schedule(callable, 1, TimeUnit.SECONDS);
        threadPoolSchedule.scheduleAtFixedRate(runnable, 2, 5, TimeUnit.SECONDS);
        //以固定延迟运行 withFixed
        threadPoolSchedule.scheduleWithFixedDelay(runnable, 2, 5, TimeUnit.SECONDS);

        for (int i = 0; i < 5; i++) {
            try {

                scheduledFuture.get(3, TimeUnit.SECONDS);
                System.out.println("运行次数 = [" + Thread.currentThread().getName() + "]");
            } catch (ExecutionException e) {
                System.out.println("[" + "执行异常" + "]");
            } catch (TimeoutException e) {
                System.out.println("获取超时");
            } catch (InterruptedException e) {
                System.out.println("  [" + "异常中断" + "]");
            }

        }
        // 任务执行完毕，关闭线程池
        threadPoolFixed.shutdown();
        threadPoolCached.shutdown();
        threadPoolSingle.shutdown();
        threadPoolSchedule.shutdown();
    }

    @Test
    public void TestEs() {
        data.get();
        data.set(11);
        data.get();
        data.set(12);
        data.get();
    }
}

class Data {
    private int data;// 共享数据
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    void set(int data) {
        rwl.writeLock().lock();// 取到写锁
        try {
            System.out.println(Thread.currentThread().getName() + "准备写入数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);
        } finally {
            rwl.writeLock().unlock();// 释放写锁
        }
    }

    void get() {
        rwl.readLock().lock();// 取到读锁
        try {
            System.out.println(Thread.currentThread().getName() + "准备读取数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "读取" + this.data);
        } finally {
            rwl.readLock().unlock();// 释放读锁
        }
    }
}








