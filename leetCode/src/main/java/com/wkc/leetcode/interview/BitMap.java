package com.wkc.leetcode.interview;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created on 2022/3/14.
 *
 * @author Weikaichen
 */
@Slf4j
@SuppressWarnings(value = "all")
public class BitMap {
    //10000000数据
    //2022-03-15 10:35:21.800 [main] DEBUG com.wkc.leetcode.interview.BitMap - 构造数据开始=========>
    //2022-03-15 10:35:36.837 [main] DEBUG com.wkc.leetcode.interview.BitMap - 构造数据开始结束，耗时: 15038ms
    //2022-03-15 10:35:36.838 [main] DEBUG com.wkc.leetcode.interview.BitMap - bitmap开始=========>
    //2022-03-15 10:37:33.294 [main] DEBUG com.wkc.leetcode.interview.BitMap - bitmap 去重结果：9681050
    //2022-03-15 10:37:33.295 [main] DEBUG com.wkc.leetcode.interview.BitMap - bitmap结束，耗时: 116457ms
    //2022-03-15 10:37:33.295 [main] DEBUG com.wkc.leetcode.interview.BitMap - hashset开始=========>
    //2022-03-15 10:39:39.342 [main] DEBUG com.wkc.leetcode.interview.BitMap - hashset 去重结果：9681050
    //2022-03-15 10:39:39.342 [main] DEBUG com.wkc.leetcode.interview.BitMap - hashset结束，耗时: 126047ms
    public static void main(String[] args) {
        BitMap.test(1000);
    }

    public static void test(int num) {
        long start = System.currentTimeMillis();
        log.debug("构造数据开始=========>");
        createFile(num);
        log.debug("构造数据开始结束，耗时: {}ms", System.currentTimeMillis() - start);

        long start1 = System.currentTimeMillis();
        log.debug("bitmap开始=========>");
        bitmap();
        log.debug("bitmap结束，耗时: {}ms", System.currentTimeMillis() - start1);


        long start2 = System.currentTimeMillis();
        log.debug("hashset开始=========>");
        hashset();
        log.debug("hashset结束，耗时: {}ms", System.currentTimeMillis() - start2);
    }

    private static void hashset() {
        try (RandomAccessFile w = new RandomAccessFile("leetCode/data.txt", "r")) {
            HashSet<String> strings = new HashSet<>();
            while (true) {
                String s = w.readLine();
                if (s == null) {
                    break;
                }
                strings.add(s);
            }
            log.debug("hashset 去重结果：{}", strings.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bitmap() {
        try (RandomAccessFile w = new RandomAccessFile("leetCode/data.txt", "r")) {
            int count = 0;
            HashMap<String, BitSet> bitmap = new HashMap<>();
            while (true) {
                String s = w.readLine();
                if (s == null) {
                    break;
                }
                BitSet bitSet = bitmap.computeIfAbsent(s.substring(0, 3), k -> new BitSet());
                bitSet.set(Integer.parseInt(s.substring(3)));
            }
            for (BitSet value : bitmap.values()) {
                count += value.cardinality();
            }
            log.debug("bitmap 去重结果：{}", count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile(int count) {
        try (RandomAccessFile w = new RandomAccessFile("leetCode/data.txt", "rw")) {
            for (int i = 0; i < count; i++) {
                w.writeBytes(getTel());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    private static final String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    private static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third + "\n";
    }
}
