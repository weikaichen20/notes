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
        Poker.start(8);
        Poker.getFlop(true);
        Poker.getPlayer().forEach(p -> p.lookHand());
        Poker.getMaxCards();
    }
}
