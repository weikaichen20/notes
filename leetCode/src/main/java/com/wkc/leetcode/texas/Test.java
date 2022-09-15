package com.wkc.leetcode.texas;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created on 2022/3/28.
 *
 * @author Weikaichen
 */
public class Test {
    public static void main(String[] args) {
        int count=0;
        while (true){
            Stack<Card> cards = Poker.POKER;
            ArrayList<Card> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                list.add(cards.pop());
            }
            boolean b = Poker.checkFlushStraight(list);
            count++;
            if (b) {
                System.out.println(list + "=====>" + count);
            }
        }
    }
}
