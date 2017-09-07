package com.cn.nlg.lp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/7 </pre>
 */

class Kata {

    /*
     * question1 输入两个字符串，长度分别为len1，len2以短的那个为标准 从前向后比较，输出相同的元素
     */
    String q1(String s1, String s2) {
        if (s1.length() < s2.length()) {
            String temp = s2;
            s2 = s1;
            s1 = temp;
        }
        Map<String, Object> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            for (char c1 : s2.toCharArray()) {
                if (c == c1) {
                    map.put("" + c, c);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        map.forEach((k, v) -> result.append(k));
        return result.toString();
    }

    /*
     * question2  找出字符串中出现次数为偶数的字母和出现的次数
     *      此方法含义为 如果使用key获取到的值为null 则用参数value替换，否则使用声明的函数运算，用结果替换原来value值
     等效于
          Integer value;
          value=map.get("" + c);
          if(null==value) map.put("" + c, 1);
          else map.put("" + c, value + 1); <code>
     *
     */
    Map<String, Integer> q2(String s) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (char c : s.toCharArray()) {
            map.merge("" + c, 1, (old, v) -> old + v);
        }
        map.forEach((k, v) -> {
            if (v % 2 != 0) map.remove(k);
        });
        return map;
    }

    Map<Integer, Integer> q3(Integer[] highth) {
        Arrays.sort(highth);
        Map<Integer, Integer> map = new HashMap<>(2);
        map.put(highth[highth.length - 1], highth[1]);
        return map;
    }
}
