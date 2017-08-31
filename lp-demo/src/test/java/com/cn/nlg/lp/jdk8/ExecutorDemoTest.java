package com.cn.nlg.lp.jdk8;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ExecutorDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>八月 31, 2017</pre>
 */
public class ExecutorDemoTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: execute(Runnable runnable)
     * 使用execute 是在当前线程里面执行任务 使用Thread是在新线程里面执行任务。
     * 使用execute可以有效的让线程和任务解耦 即创建任务和执行任务分离
     */
    @Test
    public void testExecute() throws Exception {

        ExecutorDemo executorDemo = new ExecutorDemo();
        executorDemo.execute(() -> System.out.println("name=" + Thread.currentThread().getName()));
        Thread thread = new Thread(() -> System.out.println("name=" + Thread.currentThread().getName()));
        thread.start();

    }


} 
