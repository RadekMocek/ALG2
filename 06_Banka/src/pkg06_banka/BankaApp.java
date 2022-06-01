package pkg06_banka;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankaApp {

    static Scanner sc = new Scanner(System.in);

    static Ucet acc;

    public static void main(String[] args) {

        System.out.println("Zadejte číslo účtu:");

        int accNum = inputInteger();

        acc = new Ucet(accNum);

        // Menu
        boolean end = false;
        int choice;
        int day, month, year, sum;
        while (!end) {
            // Vypsat menu
            String menu = """
                          == BANKA ==
                          1. Zadat jiné číslo účtu (momentálně %d)
                          2. Provést vklad
                          3. Provést výběr
                          4. Výpočet bilance
                          5. Výpis z účtu
                          0. Ukončit aplikaci""".formatted(acc.getAccNum());

            System.out.println(menu);

            // Vstup od uživatele
            choice = inputInteger();

            switch(choice) {
                case 1 -> {
                    System.out.println("Zadejte nové číslo účtu:");
                    accNum = inputInteger();
                    acc = new Ucet(accNum);
                }
                case 2,3 -> {
                    System.out.println("Zadejte den:");
                    day = inputIntegerInRange(1, 31);
                    System.out.println("Zadejte měsíc:");
                    month = inputIntegerInRange(1, 12);
                    System.out.println("Zadejte rok:");
                    year = inputIntegerInRange(1970, 2022);

                    if (choice == 2) {
                        System.out.println("Zadejte vklad:");
                        sum = inputInteger();
                        try {
                            acc.depositWithdraw(day, month, year, true, sum);
                        } catch (IOException ex) {
                            System.out.println("Chyba: " + ex);
                        }
                    }
                    else if (choice == 3) {
                        System.out.println("Zadejte výběr:");
                        sum = inputInteger();
                        try {
                            acc.depositWithdraw(day, month, year, false, sum);
                        } catch (IOException ex) {
                            System.out.println("Chyba: " + ex);
                        }
                    }
                }
                case 5 -> {
                    try {
                        System.out.println(acc.printout());
                    } catch (IOException ex) {
                        System.out.println("Chyba: " + ex);
                    }
                }
                case 0 -> end = true;
                default -> System.out.println("Neplatný příkaz.");
            }

        }

    }

    public static int inputIntegerInRange(int min, int max) {
        if (max < min) throw new IllegalArgumentException("Špatně zadaný rozsah.");
        while (true) {
            int input = inputInteger();
            if (input >= min && input <= max) {
                return input;
            }
            else {
                System.out.printf("Zadejte číslo v rozsahu %d až %d:%n", min, max);
            }
        }
    }

    public static int inputInteger() {
        String input;
        while (true) {
            input = sc.nextLine();
            if (tryParseToInt(input)) {
                return Integer.parseInt(input);
            }
            else {
                System.out.println("Zadejte prosím číslo:");
            }
        }
    }

    public static boolean tryParseToInt(String text) {
        try {
            Integer.parseInt(text);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

}
