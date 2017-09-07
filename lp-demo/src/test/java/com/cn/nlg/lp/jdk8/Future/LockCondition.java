package com.cn.nlg.lp.jdk8.Future;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/7 </pre>
 */
public class LockCondition {
    @Test
    public void TestLockCondition() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        final Business2 business = new Business2();
        es.execute(() -> {//提交5个business.sub方法 作为一个Runable
            for (int i = 0; i < 5; i++) {
                business.sub();
            }
        });
        es.execute(() -> {//提交5个business.main 作为一个Runable执行
                    for (int i = 0; i < 5; i++) {//执行5次business.main方法
                        business.main();
                    }
                }
        );
    }

}

class Business2 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();//得到当前锁阻塞条件
    private boolean isSub = true; //默认一开始限制性sub任务

    void sub() {
        lock.lock();
        while (!isSub) {//不是sub执行条件，则进入进行阻塞处理
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
        isSub = false;
        condition.signal();
        lock.unlock();
    }


    void main() {
        lock.lock();
        if (isSub) { //是sub执行任务，则进入阻塞main任务
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
        isSub = true;
        condition.signal();
        lock.unlock();
    }
}
