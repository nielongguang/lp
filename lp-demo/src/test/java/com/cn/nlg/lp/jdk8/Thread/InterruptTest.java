package com.cn.nlg.lp.jdk8.Thread;

import java.util.concurrent.Executor;

/**
 * ${entry.className} Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/6 </pre>
 */

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
//        MyThread t = new MyThread("MyThread");
//        t.start();
//        t.interrupt();// 中断t线程
//        System.out.println("interrupt = [" + t.isInterrupted() + "]");

        // 创建线程对象
//        YieldThread t1 = new YieldThread("t1");
//        YieldThread t2 = new YieldThread("t2");
//        // 启动线程
//        t1.start();
//        t2.start();
//        // 主线程休眠100毫秒
//        Thread.sleep(100);
//        // 终止线程
//        t1.interrupt();
//        t2.interrupt();


        JoinThread t1 = new JoinThread("t1");
        JoinThread t2 = new JoinThread("t2");
        t1.start();
        t2.start();
        Runnable r = () -> {
            try {
                t1.join();
                t2.join();
                Thread.sleep(1000);
                System.out.println("name = [" + Thread.currentThread().getName() + "]");
            } catch (InterruptedException e) {
                System.out.println("e = [" + e + "]");
            }
        };
        Executor executor = Runnable::run;
        executor.execute(r);
        System.out.println("主线程开始执行！");
    }

}

class MyThread extends Thread {
    int i = 0;

    MyThread(String name) {
        super(name);
    }

    public void run() {
        while (true) {// 死循环，等待被中断
            System.out.println(getName() + getId() + "执行了" + ++i + "次");
            try {
                Thread.sleep(1000);
                System.out.println("name = [" + Thread.currentThread().getName() + "]");
            } catch (InterruptedException e) {
                System.out.println("e = [" + e + "]");
            }
        }
    }
}

class YieldThread extends Thread {
    int i = 0;


    public YieldThread(String name) {
        super(name);
    }

    public void run() {
        while (!isInterrupted()) {
            System.out.println(getName() + "执行了" + ++i + "次");
            if (i % 10 == 0) {// 当i能对10整除时，则让步
                Thread.yield();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("异常中断异常");
                }

            }
        }
    }
}

class JoinThread extends Thread {
    public JoinThread(String name) {
        super(name);
    }

    public void run() {
        for (int i = 1; i <= 10; i++)
            System.out.println(getName() + ":" + getId() + "执行了" + i + "次");
    }
}