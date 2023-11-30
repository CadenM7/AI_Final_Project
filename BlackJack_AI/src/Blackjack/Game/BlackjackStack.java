package Blackjack.Game;

import java.util.Random;

public class BlackjackStack {

    private int top;

    private Card[] deck;

    public BlackjackStack(){this(52);}

    public BlackjackStack(int size) {
        top = 0;
        deck = new Card[size];

        for(CardSuits s: CardSuits.values()) {
            for(CardValues v: CardValues.values()) {
                Card card = new Card(v,s);
                push(card);
            }
        }
    }

    public int size() {return top;}

    public Card pop() {
        emptyCheck();
        top--;
        Card temp = deck[top];
        return temp;
    }
    public Card peek() {
        emptyCheck();
        return deck[top - 1];
    }

    public void push(Card d) {
        resize();
        deck[top] = d;
        top++;
    }

    private void resize() {
        if (top == deck.length) {
            Card[] deck2 = new Card[deck.length * 2];
            for (int i = 0; i < deck.length; i++) {
                deck2[i] = deck[i];
            }
            deck = deck2;
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < top; i++) {
            s += deck[i] + " ";
        }
        return s;
    }

    public int capacity() {
        return deck.length;
    }

    public void emptyCheck() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot access from empty stack");
        }
    }
    public boolean isEmpty() {
        return size() == 0;
    }

    public void shuffle() {
        for (int i = 0; i < size(); i++) {
            Random r = new Random();
            int index = r.ints(i, size()).findFirst().getAsInt();
            Card temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }
    }
}


