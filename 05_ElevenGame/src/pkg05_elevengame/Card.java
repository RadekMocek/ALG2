package pkg05_elevengame;

public class Card {

    private String value;
    private int points;
    private Symbol symbol;

    public Card(String value, Symbol symbol) {
        this.symbol = symbol;
        this.value = value;
        this.points = switch(this.value) {
            case "A" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6" -> 6;
            case "7" -> 7;
            case "8" -> 8;
            case "9" -> 9;
            case "10" -> 10;
            case "J", "Q", "K" -> 0;
            default -> 0;
        };
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        String symbol_s = switch(this.symbol) {
            case club -> "♣";
            case diamond -> "♦";
            case heart -> "♥";
            case spade -> "♠";
        };
        return symbol_s + " " + value;
    }

}
