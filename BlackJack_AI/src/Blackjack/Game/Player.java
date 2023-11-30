package Blackjack.Game;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;

    public Player() {
         hand = new ArrayList<Card>();
    }
    public void add(Card card) {
        hand.add(card);
    }

    public int handValue() {
        int value = 0;
        for(int i = 0; i < hand.size(); i++) {
            value += hand.get(i).getCardValues();
        }
        return value;
    }

    public int tv() {
        int v = 0;
        for(int i = 0; i < hand.size(); i++) {
            v += hand.get(i).getCardTV();
        }
        return v;
    }

    public int getLength() {
        return hand.size();
    }

    public Card get(int x) {
        return hand.get(x);

    }
}
