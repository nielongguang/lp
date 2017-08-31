package com.cn.nlg.lp.jdk8;

import java.util.concurrent.Executor;

/**
 * ${entry.className} Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/8/31 </pre>
 */
public class ExecutorDemo implements Executor {


    @Override
    public void execute(Runnable runnable) {
        System.out.println("runnable = [" + runnable + "]");
        runnable.run();
    }
}
