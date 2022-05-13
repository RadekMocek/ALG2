# ElevenGame

Vytvořte projekt ElevenGame, kde vytvoříte a implementujete částečný objektový model pro karetní hru 11.

Karetní hra 11 se hraje s balíčkem 52 karet s hodnotami A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K  a symboly ♣ (kříž), ♦ (káro), ♥ (srdce) a ♠ (list). Hraje jeden hráč. Pravidla hry:

* Balíček karet se zamíchá a 9 karet se rozloží na stůl hodnotami nahoru.
* Pokud je to možné, hráč opakuje následující kroky:
  * Hráč odstraní dvojici karet, kterých součet hodnot je dohromady 11 například 3 a 8, nebo 6 a 5. Karta A se počítá za 1 bod a J, Q, K za 0. Na symbolech nezáleží.
  * Nebo hráč odstráni trojici J, Q, K. Na symbolech nezáleží.
  * Pokud jsou v balíčku karty, nahradí se odstraněné karty ze stolů kartami z balíčku.

Hráč vyhrál, pokud v balíčku ani na stole nezůstaly žádné karty.
