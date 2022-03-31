package com.wkc.leetcode.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created on 2022/1/26.
 *
 * @author Weikaichen
 */
public class Stream {
    public static void main(String[] args) {
//        String result = "result".replaceAll("&(?![a-z]+;)", "&amp;");
        //https://www.cnblogs.com/dogecheng/p/11466687.html?msclkid=4ac87a70af4411ecb8dd8a85f56a8a68
//        System.out.println(Pattern.matches("index(?:1|2)/", "index1"));
//        System.out.println(Pattern.matches("index(?:1|2)", "index2"));
//        System.out.println(Pattern.matches("index(?:1|2)", "index"));
    }

    public int removeDuplicates(int[] nums) {
        ArrayList<Integer> a = (ArrayList<Integer>) new ArrayList<Integer>(Arrays.asList(Arrays.stream(nums).boxed().toArray(Integer[]::new))).stream().distinct().collect(Collectors.toList());
        return a.size();
    }
}
