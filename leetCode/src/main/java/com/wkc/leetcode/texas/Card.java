package com.wkc.leetcode.texas;

import lombok.Data;

/**
 * Created on 2022/3/28.
 *
 * @author Weikaichen
 */
@Data
public class Card {
    private int number;

    private int sn;

    private String numStr;

    private String suit;

    public Card(int number, int sn, String numStr, String suit) {
        this.number = number;
        this.sn = sn;
        this.numStr = numStr;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return numStr + suit;
    }
}
