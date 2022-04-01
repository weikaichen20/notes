package com.wkc.leetcode.texas;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created on 2022/3/28.
 *
 * @author Weikaichen
 */
public class Player {
    private ArrayList<Card> handCard;
    private String name;
    private BigDecimal chips;
    private BigDecimal account;
    private String maxHand;

    public Player(ArrayList<Card> handCard, String name, BigDecimal chips, BigDecimal account) {
        this.handCard = handCard;
        this.name = name;
        this.chips = chips;
        this.account = account;
    }

    public Player(ArrayList<Card> handCard, String name) {
        this.handCard = handCard;
        this.name = name;
    }

    public ArrayList<Card> getHandCard() {
        return handCard;
    }

    public void setHandCard(ArrayList<Card> handCard) {
        this.handCard = handCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getChips() {
        return chips;
    }

    public void setChips(BigDecimal chips) {
        this.chips = chips;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public void lookHand() {
        System.out.println(name + handCard);
    }


    public String getMaxHand() {
        return maxHand;
    }

    public void setMaxHand(String maxHand) {
        this.maxHand = maxHand;
    }
}
