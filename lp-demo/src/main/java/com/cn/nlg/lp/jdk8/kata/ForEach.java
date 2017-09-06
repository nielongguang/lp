package com.cn.nlg.lp.jdk8.kata;

import com.cn.nlg.lp.jdk8.ForEachDemo;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * ${entry.className} Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/1 </pre>
 */

@Slf4j
@NoArgsConstructor
public class ForEach {

    private ForEachDemo forEachDemo = new ForEachDemo();
    private List<String> list = forEachDemo.getList();
    private Map<String, Integer> map = forEachDemo.getMap();
    private Runnable runnable1 = ForEach.this::handleList;
    private Runnable runnable2 = ForEach.this::handleMap;
    private Runnable runnable3 = ForEach.this::handleMap;
    private Executor executor = Runnable::run;


    private void handleList() {
        list.forEach((k) -> System.out.println("k" + k));
    }

    private void handleMap() {
        map.forEach((k, v) -> System.out.println("k: " + k + " v:" + v));
    }

    private void handleMapBase() {

        for (Map.Entry entry : map.entrySet()) {
            System.out.printf("k:" + entry.getKey());
            System.out.println("v" + entry.getValue());
        }
    }


    public void execute() {
        log.debug("run :runnable1");
        executor.execute(runnable1);
        log.debug("run :runnable2");
        executor.execute(runnable2);
        log.debug("run :runnable3");
        executor.execute(runnable3);
    }


}
