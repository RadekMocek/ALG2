package pkg05_elevengame;

import java.util.List;
import java.util.Scanner;

public class ConsolaUI {
    
    Scanner sc = new Scanner(System.in);
    Game game;
    
    public void start() {
        boolean nextGame = true;
        while (nextGame) {
            game = new Game();
            playGame();
            System.out.println("Chcete hrát znovu?");
            nextGame = sc.next().equalsIgnoreCase("y");
        }
    }
    
    private void playGame() {
        System.out.println("Vítejte ve hře " + game.getName() + "!");
        
        displayDeck();
        displayCards();
        
        while (game.anotherPlayIsPossible()) {
            System.out.println("Výběr karty:");
            String[] selectedCards = sc.nextLine().split(" ");

            List<Integer> iSelectedCards = toInt(selectedCards);

            if (game.playAndReplace(iSelectedCards)) {
                displayDeck();
                displayCards();
            }
            else {
                System.out.println("Nevalidní tah!");
            }
        }
        
        if (game.isWon()) {
            System.out.println("Gratulejeme!");
        }
        else {
            System.out.println("Nelze vyhrát :-(");
        }
    }
    
    private void displayDeck() {
        System.out.println("V balíčku je " + game.getDeckSize() + " karet.");
    }
    
    private void displayCards() {
        for (int i = 1; i <= game.nCards(); i++) {
            System.out.printf("%1d. %10s   ", i, game.getCardDescription(i-1));
        }
    }
    
    private List<Integer> toInt(String[] cards) {
        throw new UnsupportedOperationException();
    }
    
}
