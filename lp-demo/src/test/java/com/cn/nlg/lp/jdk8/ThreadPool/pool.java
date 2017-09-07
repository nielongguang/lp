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
    //     1.  ����һ�������ù̶��̼߳��ϵ��̳߳أ��Թ�����޽���з�ʽ��������Щ�߳�,���߳�û�г�ʱʱ�䣬�̳߳ز����Զ����Ŵ�С
    static ExecutorService threadPoolFixed = Executors.newFixedThreadPool(3);//
    //     2. ����һ���ɸ�����Ҫ�������̵߳��̳߳أ���������ǰ������߳̿���ʱ���������ǣ���ʱʱ��Ϊ60�룬���������̶߳�����ڣ����ᱣ��
    static ExecutorService threadPoolCached = Executors.newCachedThreadPool();
    //     3. ����һ��ʹ�õ��� worker �̵߳� Executor�����޽���з�ʽ�����и��̡߳�
    static ExecutorService threadPoolSingle = Executors.newSingleThreadExecutor();
    //     4. ����һ���ɰ����ڸ����ӳٺ�����������߶��ڵ�ִ�е��̳߳ء�
    static ScheduledExecutorService threadPoolSchedule = Executors.newScheduledThreadPool(3);//

    Data data = new Data();

    public static void main(String[] args) {

        Callable<String> callable = () -> {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(20);// Ϊ�˲��Գ�Ч������ÿ������ִ�ж���Ҫһ��ʱ��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ʱ�� ��" + LocalDateTime.now().toString() + "��ִ��" + Thread.currentThread().getName());
            }
            return "hello";
        };
        Runnable runnable = () -> {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(20);// Ϊ�˲��Գ�Ч������ÿ������ִ�ж���Ҫһ��ʱ��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ʱ�䣺" + LocalDateTime.now().toString() + " ��ִ��" + Thread.currentThread().getName());

            }
        };

        ScheduledFuture scheduledFuture = null;

        threadPoolFixed.submit(callable);
        threadPoolCached.submit(callable);
        threadPoolSingle.submit(callable);
        threadPoolSchedule.schedule(runnable, 10, TimeUnit.SECONDS);
        scheduledFuture = threadPoolSchedule.schedule(callable, 1, TimeUnit.SECONDS);
        threadPoolSchedule.scheduleAtFixedRate(runnable, 2, 5, TimeUnit.SECONDS);
        //�Թ̶��ӳ����� withFixed
        threadPoolSchedule.scheduleWithFixedDelay(runnable, 2, 5, TimeUnit.SECONDS);

        for (int i = 0; i < 5; i++) {
            try {

                scheduledFuture.get(3, TimeUnit.SECONDS);
                System.out.println("���д��� = [" + Thread.currentThread().getName() + "]");
            } catch (ExecutionException e) {
                System.out.println("[" + "ִ���쳣" + "]");
            } catch (TimeoutException e) {
                System.out.println("��ȡ��ʱ");
            } catch (InterruptedException e) {
                System.out.println("  [" + "�쳣�ж�" + "]");
            }

        }
        // ����ִ����ϣ��ر��̳߳�
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
    private int data;// ��������
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    void set(int data) {
        rwl.writeLock().lock();// ȡ��д��
        try {
            System.out.println(Thread.currentThread().getName() + "׼��д������");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "д��" + this.data);
        } finally {
            rwl.writeLock().unlock();// �ͷ�д��
        }
    }

    void get() {
        rwl.readLock().lock();// ȡ������
        try {
            System.out.println(Thread.currentThread().getName() + "׼����ȡ����");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "��ȡ" + this.data);
        } finally {
            rwl.readLock().unlock();// �ͷŶ���
        }
    }
}








