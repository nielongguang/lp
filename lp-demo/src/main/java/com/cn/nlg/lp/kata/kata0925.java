package com.cn.nlg.lp.kata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/25 </pre>
 */
public class kata0925 {
    public static void main(String[] args) {
        int[] arr = {2, 41, 5, 2345, 62, 1};
        kata0925 kata = new kata0925();
        out.println(Arrays.toString(kata.sort1(arr)));
    }


    //计算每个字符出现的次数
    public Map<String, Integer> countNum(String var1) {
        Map<String, Integer> map = new HashMap<>();
        map.merge(var1, 1, (x, y) -> x + y);
        return map;
    }

    //冒泡排序 降序
    private int[] sort(int[] var1) {
        for (int i = 0; i < var1.length; i++) {
            for (int j = 0; j < var1.length - 1; j++) {
                if (var1[i] >= var1[j + 1]) {
                    int temp = var1[i];
                    var1[i] = var1[j + 1];
                    var1[j + 1] = temp;
                }
            }
        }
        return var1;
    }

    //选择排序 降序
    private int[] sort1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {// 做第i趟排序
            int k = i;
            for (int j = i + 1; j < arr.length; j++) {// 选最小的记录
                if (arr[j] > arr[k]) {
                    k = j; //记下目前找到的最小值所在的位置
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            if (i != k) {  //交换a[i]和a[k]
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;

            }
        }
        return arr;
    }


}
