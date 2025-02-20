package com.learnjava.java8;

import java.util.Arrays;
import java.util.List;

public class LambdaTest3 {

    /**
     * Stream实例
     */

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");


        strings.stream() //创建流
                // 过滤以"a"开头的字符串
                .filter(s -> s.startsWith("a"))
                //转换成大写 String::toUpperCase 等同于  string -> string.toUpperCase()
                .map(String::toUpperCase)
                //排序
                .sorted()
                // for循环打印
                .forEach(System.out::println);
    }
}
