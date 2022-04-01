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
//        Poker.start(8);
//        Poker.getFlop(true);
//        Poker.getPlayer().forEach(p -> p.lookHand());
//        Poker.getMaxCards();
        int count=0;
        while (true){
            Stack<Card> cards = Poker.newPoker();
            ArrayList<Card> list = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                list.add(cards.pop());
            }
            boolean b = Poker.checkFlush(list);
            boolean b1 = Poker.checkStraight(list);
            count++;
            if (b1&b){
                System.out.println(list+"=====>"+count);
            }
        }
//        ArrayList<Card> list = new ArrayList<>();
//        list.add(new Card(4, 1, "4", "♠"));
//        list.add(new Card(5,4 , "5", "♦"));
//        list.add(new Card(11, 2, "J", "♣"));
//        list.add(new Card(11, 4, "J", "♦"));
//        list.add(new Card(13, 3, "K", "♥"));
//        boolean b1 = Poker.checkStraight(list);
//        System.out.println(b1);

    }
}
