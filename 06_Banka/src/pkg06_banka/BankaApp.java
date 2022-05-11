package pkg06_banka;

public class BankaApp {

    public static void main(String[] args) {

        displayMenu();

    }

    private static void displayMenu() {
        String menu = """
                      1. Zadání čísla účtu
                      2. Provedení vkladu
                      3. Provedení výběru
                      4. Zobrazení výpisu z účtu
                      5. Výpočet bilance
                      0. Ukončit
                      """;
        System.out.println(menu);
    }

}
