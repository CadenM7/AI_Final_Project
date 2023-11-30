package Blackjack.Game;

public enum CardValues {
    // Values
    TWO(2, 2),
    THREE(3, 3),
    FOUR(4, 4),
    FIVE(5, 5),
    SIX(6, 6),
    SEVEN(7, 7),
    EIGHT(8, 8),
    NINE(9, 9),
    TEN(10, 10),
    JACK(11, 10),
    QUEEN(12, 10),
    KING(13, 10),
    ACE(14, 11);

    private int value;

    private int tv;

    public int getValue() {return value;}

    public int getTV() {return tv;}

    private CardValues(int value, int tv) {
        this.value = value;
        this.tv = tv;
    }
}
