package com.cn.nlg.lp.jdk8.Thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ${entry.className} Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/6 </pre>
 */
public class TimerTaskDemo {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask2(), 2000);
        while (true) {
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    static class MyTimerTask1 extends TimerTask {
        public void run() {
            System.out.println("±¬Õ¨£¡£¡£¡");
//        new Timer().schedule(new MyTimerTask2(), 2000);
        }
    }

    static class MyTimerTask2 extends TimerTask {
        public void run() {
            System.out.println("±¬Õ¨£¡£¡£¡");
//        new Timer().schedule(new MyTimerTask1(), 3000);
        }
    }
}


