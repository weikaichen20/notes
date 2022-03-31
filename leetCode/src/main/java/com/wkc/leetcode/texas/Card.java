package com.wkc.leetcode.texas;

/**
 * Created on 2022/3/28.
 *
 * @author Weikaichen
 */
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getNumStr() {
        return numStr;
    }

    public void setNumStr(String numStr) {
        this.numStr = numStr;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "\t" + numStr + suit + "\t";
    }
}
