package com.cn.nlg.lp.jdk8;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
public class ForEachDemo {

    Map<String, Integer> map = new HashMap<>(7);
    List<String> list = new ArrayList<>(7);

    public ForEachDemo() {
        log.debug(">>>>---run");
        this.put();
    }

    public void put() {
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        map.put("D", 40);
        map.put("E", 50);
        map.put("F", 60);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

    }

    public void foreachBase() {
        System.out.println("old>>>>>>>>");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey() + ";value:" + entry.getValue());
        }
        for (String value : list) {
            System.out.println("value:" + value);
        }
    }

    public void foreachNew() {
        System.out.println("new>>>>>>>>");
        map.forEach((k, v) -> System.out.println("key : " + k + "; value : " + v));
        list.forEach((k) -> System.out.printf("key : " + k));
//        new Thread(() ->System.out.println("hello"));

    }


}
