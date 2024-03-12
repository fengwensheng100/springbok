package cn.code4java.springbok.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName ListUtils
 * @Description: List工具类
 * @Author fengwensheng
 * @Date 2024/1/26
 * @Version V1.0
 **/
public class ListUtils {

    public static <T> List<T> page(List<T> list, long current, long size) {
        int begin = (int) (current > 0 ? ((current - 1) * size) : 0);
        int end = (int) (current > 0 ? current * size : size);
        return list.subList(begin, list.size() > end ? end : list.size());
    }

    public static List<Integer> toListAsInt(String[] arr){
        List<Integer> integers = new LinkedList<>();
        for (String a : arr) {
            integers.add(Integer.valueOf(a));
        }
        return integers;
    }
}
