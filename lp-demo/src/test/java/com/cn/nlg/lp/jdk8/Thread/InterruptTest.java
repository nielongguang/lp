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
//        t.interrupt();// �ж�t�߳�
//        System.out.println("interrupt = [" + t.isInterrupted() + "]");

        // �����̶߳���
//        YieldThread t1 = new YieldThread("t1");
//        YieldThread t2 = new YieldThread("t2");
//        // �����߳�
//        t1.start();
//        t2.start();
//        // ���߳�����100����
//        Thread.sleep(100);
//        // ��ֹ�߳�
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
        System.out.println("���߳̿�ʼִ�У�");
    }

}

class MyThread extends Thread {
    int i = 0;

    MyThread(String name) {
        super(name);
    }

    public void run() {
        while (true) {// ��ѭ�����ȴ����ж�
            System.out.println(getName() + getId() + "ִ����" + ++i + "��");
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
            System.out.println(getName() + "ִ����" + ++i + "��");
            if (i % 10 == 0) {// ��i�ܶ�10����ʱ�����ò�
                Thread.yield();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("�쳣�ж��쳣");
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
            System.out.println(getName() + ":" + getId() + "ִ����" + i + "��");
    }
}