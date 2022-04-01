package com.wkc.leetcode.texas;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2022/3/28.
 *
 * @author Weikaichen
 */
@Slf4j
@SuppressWarnings("all")
public final class Poker {
    private final static String[] NUMBER = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private final static String[] SUIT = {"♠", "♣", "♥", "♦"};

    private static Stack<Card> POKER = new Stack<>();

    private static ArrayList<Player> player = new ArrayList<>();

    public volatile static int number;
    private final static ArrayList<Card> pubCard = new ArrayList<>();

    static {
        getPokerCard();
    }

    private static Stack<Card> getPokerCard() {
        ArrayList<Card> cards = new ArrayList<>();
        for (String n : NUMBER) {
            for (String s : SUIT) {
                Card card = new Card(getNumber(n), getSn(s), n, s);
                cards.add(card);
            }
        }
        Collections.shuffle(cards);
        cards.forEach(p -> POKER.push(p));
        return POKER;
    }

    private static int getNumber(String n) {
        switch (n) {
            case "A":
                return 14;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            default:
                break;
        }
        return 0;
    }

    private static int getSn(String sn) {
        switch (sn) {
            case "♠":
                return 1;
            case "♣":
                return 2;
            case "♥":
                return 3;
            case "♦":
                return 4;
            default:
                break;
        }
        return 0;
    }

    public static Stack<Card> newPoker() {
        POKER.clear();
        return getPokerCard();

    }

    public static ArrayList<Player> start(int num) {
        //shuffle
        getPokerCard();
        //start
        number = num;
        if (num > 1 && num < 9) {
            Licensing(num);
        } else {
            log.debug("人数不对，无法开始游戏");
        }
        return player;
    }

    public static void getFlop(boolean print) {
        POKER.pop();
        Card pop = POKER.pop();
        Card pop1 = POKER.pop();
        Card pop2 = POKER.pop();
        pubCard.add(pop);
        pubCard.add(pop1);
        pubCard.add(pop2);
        if (print) {
            System.out.println(pubCard);
        }
    }

    public static void getTrun(boolean print) {
        POKER.pop();
        Card pop = POKER.pop();
        pubCard.add(pop);
        if (print) {
            System.out.println(pubCard);
        }
    }

    public static void getRiver(boolean print) {
        POKER.pop();
        Card pop = POKER.pop();
        pubCard.add(pop);
        if (print) {
            System.out.println(pubCard);
        }
    }

    public static void getAllPubCards(boolean print) {
        getFlop(false);
        getTrun(false);
        getRiver(false);
        if (print) {
            System.out.println(pubCard);
        }
    }

    private static void Licensing(int num) {
        for (int i = 0; i < num; i++) {
            ArrayList<Card> cards = new ArrayList<>();
            cards.add(POKER.elementAt(POKER.size() - 1 - i));
            cards.add(POKER.elementAt(POKER.size() - 9 - i));
            Player play = new Player(cards, "player" + i);
            player.add(play);
        }
        for (int i = 0; i < 16; i++) {
            POKER.pop();
        }
    }

    public static ArrayList<Card> getPubCard() {
        return pubCard;
    }

    public static ArrayList<Player> getPlayer() {
        return player;
    }


    public static void getMaxCards() {
        getPlayer().forEach(p -> {
            ArrayList<Card> handCard = p.getHandCard();
            ArrayList<Card> playerMaxHandCard = getPlayerMaxHandCard(handCard, pubCard);
            System.out.println(playerMaxHandCard);
        });
    }

    private static ArrayList<Card> getPlayerMaxHandCard(ArrayList<Card> handCard, ArrayList<Card> pubCard) {
        if (pubCard.size() == 3) {
            ArrayList<Card> cards = getMixSort(handCard, pubCard);
            return cards;
        } else if (pubCard.size() == 4) {
            ArrayList<Card> cards = getMixSort(handCard, pubCard);
            boolean a = checkFlush(cards);
            boolean b = checkStraight(cards);
            return null;
        } else if (pubCard.size() == 5) {
            return null;
        } else {
            return null;
        }

    }

    public static boolean checkStraight(ArrayList<Card> cards) {
        if (cards.size() < 5) return false;
        cards = getMixSort(cards);
        List<Integer> collect = cards.stream().map(c -> c.getNumber()).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            if (collect.size() - i >= 5) {
                if (collect.get(i).equals(Integer.valueOf(14))) {

                    if (collect.get(collect.size() - 1).equals(Integer.valueOf(2))) {
                        if (collect.get(collect.size() - 2).equals(3) && collect.get(collect.size() - 3).equals(4) && collect.get(collect.size() - 4).equals(5)) {
                            return true;
                        }
                    } else {
                        for (int j = i+1; j < i+5; j++) {
                            if (!(collect.get(j)==collect.get(j-1)-1)){
                                return false;
                            }
                        }
                        return true;
                    }
                    continue;
                }
                for (int j = i+1; j < i+5; j++) {
                    if (!(collect.get(j)==collect.get(j-1)-1)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean checkFlush(ArrayList<Card> cards) {
        if (cards.size() < 5) return false;
        cards = getMixSort(cards);
        List<Integer> collect = cards.stream().map(c -> c.getSn()).collect(Collectors.toList());
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        collect.forEach(s -> hashMap.put(s, hashMap.getOrDefault(s, 0) + 1));
        for (Integer s : collect) {
            if (hashMap.getOrDefault(s, 0) >= 5) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Card> getMixSort(ArrayList<Card> handCard, ArrayList<Card> pubCard) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(handCard);
        cards.addAll(pubCard);
        cards.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getNumber() == o2.getNumber() ? o1.getSn() - o2.getSn() : o2.getNumber() - o1.getNumber();
            }
        });
        return cards;
    }

    private static ArrayList<Card> getMixSort(ArrayList<Card> handCard) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(handCard);
        cards.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getNumber() == o2.getNumber() ? o1.getSn() - o2.getSn() : o2.getNumber() - o1.getNumber();
            }
        });
        return cards;
    }

}
