package com.wkc.leetcode.texas;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created on 2022/3/28.
 *
 * @author Weikaichen
 */
@Data
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

    public void lookHand() {
        System.out.println(name + handCard);
    }

}
