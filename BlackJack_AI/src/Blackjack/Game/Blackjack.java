package Blackjack.Game;

public class Blackjack {
    private Player player;

    private Player player2;

    private BlackjackStack deck;

    private int size;


    public Blackjack(int size) {
        this.size = size;
        deck = new BlackjackStack();
        deck.shuffle();

        player = new Player();
        player2 = new Player();

        player.add(deck.pop());
        player2.add(deck.pop());

        player.add(deck.pop());
        player2.add(deck.pop());

    }

    public String toString() {
        String s = "";
        for (int p1 = 0; p1 < player.getLength(); p1++) {
            s += player.handValue();
            for (int p2 = 0; p2 < player2.getLength(); p2++) {
                s += player2.handValue();
            }
            s += "\n";
        }
        return s;
    }
    public void dealerHit() {
        player2.add(deck.pop());
        player2.handValue();
    }

    public void dealerStand() {

    }

    public Card getCard(int x) {
        return player.get(x);
    }

    public Card getDealerCard(int x) {
        return player2.get(x);
    }

    public int getPlayerHandValue() {
        return player.tv();
    }

    public int getPlayer2HandValue() {
        return player2.tv();
    }

    public int handSize() {
        return player.getLength();
    }

    public int dealersHand() {
        return player2.getLength();
    }
    public void playerHit() {
        player.add(deck.pop());
    }
    public void playerStand() {
        player.tv();
    }
}


