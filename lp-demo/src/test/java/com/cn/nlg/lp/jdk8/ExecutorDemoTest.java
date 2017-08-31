package com.cn.nlg.lp.jdk8;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ExecutorDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 31, 2017</pre>
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
     * ʹ��execute ���ڵ�ǰ�߳�����ִ������ ʹ��Thread�������߳�����ִ������
     * ʹ��execute������Ч�����̺߳�������� �����������ִ���������
     */
    @Test
    public void testExecute() throws Exception {

        ExecutorDemo executorDemo = new ExecutorDemo();
        executorDemo.execute(() -> System.out.println("name=" + Thread.currentThread().getName()));
        Thread thread = new Thread(() -> System.out.println("name=" + Thread.currentThread().getName()));
        thread.start();

    }


} 
