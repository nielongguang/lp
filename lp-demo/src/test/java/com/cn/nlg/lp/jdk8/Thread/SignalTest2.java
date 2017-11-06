package com.cn.nlg.lp.jdk8.Thread;

import com.cn.nlg.lp.jdk8.Factory.Factory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class SignalTest2 {


    //����3���߳�
    @Test
    public void init() {
        final Business b = new Business();
        Factory<Runnable> factory = (o, name) -> {
            log.info(">>>>����Runable");
            return () -> {
                try {
                    Method method1 = o.getClass().getMethod(name);
                    method1.invoke(o);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    System.out.println("o = [" + o + " InvocationTargetException], name = [" + name + "]");
                } catch (IllegalAccessException e) {
                    System.out.println("o = [" + o + " IllegalAccessException ], name = [" + name + "]");
                }
            };
        };
        for (int i = 0; i < 2; i++) {


            Runnable runnable = factory.newInstans(b, "sub1");
            Runnable runnable1 = factory.newInstans(b, "sub2");
            Runnable runnable2 = factory.newInstans(b, "sub3");
            ExecutorService es = Executors.newFixedThreadPool(5);
            List<Future> list = new LinkedList<>();
            list.add(es.submit(runnable));
            list.add(es.submit(runnable1));
            list.add(es.submit(runnable2));
            list.forEach((a) -> {
                try {
                    a.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            try {
                System.out.println(es.isShutdown());
                es.shutdownNow();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    private class Business {
        int status = 1;//��ʼĬ��ִ�е�һ������
        Lock lock = new ReentrantLock();
        Condition cond1 = lock.newCondition();
        Condition cond2 = lock.newCondition();
        Condition cond3 = lock.newCondition();

        @SuppressWarnings("unused")
        public void sub1() {
            lock.lock();
            while (status != 1) {
                try {
                    cond1.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("[sub1]" + Thread.currentThread().getName() + ":" + i);
            }
            status = 2;//1ִ����ָ��2��ʼִ��
            cond2.signal();
            lock.unlock();
        }

        @SuppressWarnings("unused")
        public void sub2() {
            lock.lock();
            while (status != 2) {
                try {
                    cond2.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("[sub2]" + Thread.currentThread().getName() + ":" + i);
            }
            status = 3;//2ִ����ָ��3��ʼִ��
            cond3.signal();
            lock.unlock();
        }

        @SuppressWarnings("unused")
        public void sub3() {
            lock.lock();
            while (status != 3) {
                try {
                    cond3.await();
                } catch (InterruptedException e) {
                    System.out.println("�ж��쳣");
                }
            }
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("�ж��쳣");
                }
                System.out.println("[sub3]" + Thread.currentThread().getName() + ":" + i);
            }
            status = 1;//3ִ����ָ��1��ʼִ��
            cond1.signal();
            lock.unlock();
        }
    }
}
