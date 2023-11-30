package Blackjack.Game;

public class Card {
    private CardSuits suit;
    private CardValues values;

    public Card (CardValues values, CardSuits suit) {
        this.values = values;
        this.suit = suit;
    }
    public String getSuit() {
        return suit.name();
    }

    public void setSuit(CardSuits suit) {
        this.suit = suit;
    }

    public int getCardValues() {
        return values.getValue();
    }

    public int getCardTV() {
        return values.getTV();
    }

    public void setCardValue(CardValues cardValue)
    {
        this.values = cardValue;
    }

    public boolean equals (Object other) {
        if(other instanceof Card) {
            Card c = (Card)other;
        }
        return false;
    }
    public String ab() {
        return "" + values.getValue() + suit.name().charAt(0);
    }

}
