package com.cn.nlg.lp.jdk8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForEach {

    Map<String, Integer> items = new HashMap<>(7);
    List<String> item = new ArrayList<>(7);


    public void put() {
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);
        item.add("1");
        item.add("2");
        item.add("3");
        item.add("4");
        item.add("5");

    }

    public void foreachBase() {
        System.out.println("old>>>>>>>>");
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.println("key:" + entry.getKey() + ";value:" + entry.getValue());
        }
        for (String value : item) {
            System.out.println("value:" + value);
        }
    }

    public void foreachNew() {
        System.out.println("new>>>>>>>>");
        items.forEach((k, v) -> System.out.println("key : " + k + "; value : " + v));
        item.forEach((k) -> System.out.printf("key : " + k));
//        new Thread(() ->System.out.println("hello"));
    }

}
