package pkg05_elevengame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private List<Card> deck;
    private List<Card> table;

    private final String[] possibleValues = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public Game() {
        deck = new ArrayList<>();
        for (String s : possibleValues) {
            deck.add(new Card(s, Symbol.club));
            deck.add(new Card(s, Symbol.diamond));
            deck.add(new Card(s, Symbol.heart));
            deck.add(new Card(s, Symbol.spade));
        }
        Collections.shuffle(deck);
        table = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            drawCard();
        }
    }

    private void drawCard() {
        if (deck.isEmpty() || table.size() == 9) return;
        int index = deck.size() - 1;
        Card c = deck.get(index);
        table.add(c);
        deck.remove(index);
    }

    public String getName() {
        return "ElevenGame";
    }

    public int nCards() {
        return table.size();
    }

    public int getDeckSize() {
        return deck.size();
    }

    public String getCardDescription(int index) {
        return table.get(index).toString();
    }

    public boolean playAndReplace(List<Integer> indexes) {
        //System.out.println(indexes);
        int nChosenCards = indexes.size();
        if (nChosenCards < 2 || nChosenCards > 3) return false;
        int nCards = nCards();
        for (int i : indexes) {
            if (i < 0 || i > nCards - 1) {
                //System.out.println("Out of range");
                return false;
            }
        }
        Card c1 = table.get(indexes.get(0));
        Card c2 = table.get(indexes.get(1));
        if (nChosenCards == 3) {
            Card c3 = table.get(indexes.get(2));
            //System.out.println("Tři karty");
            if (c1.getPoints() + c2.getPoints() + c3.getPoints() == 0) {
                table.remove(c1);
                table.remove(c2);
                table.remove(c3);
                drawCard();
                drawCard();
                drawCard();
                return true;
            }
        }
        else if (nChosenCards == 2 && c1.getPoints() + c2.getPoints() == 11) {
            table.remove(c1);
            table.remove(c2);
            drawCard();
            drawCard();
            return true;
        }
        return false;
    }

    public boolean anotherPlayIsPossible() {
        int nCards = nCards();
        if (nCards < 2) return false;

        int nZeros = 0;
        for (Card c : table) {
            if (c.getPoints() == 0) nZeros++;
        }
        if (nZeros > 2) return true;

        for (int i = 0; i < nCards; i++) {
            for (int j = i; j < nCards; j++) {
                if (i == j) continue;
                Card c1 = table.get(i);
                Card c2 = table.get(j);
                if (c1.getPoints() + c2.getPoints() == 11) return true;
            }
        }

        return false;
    }

    public boolean isWon() {
        return (nCards() == 0 && getDeckSize() == 0);
    }

}
