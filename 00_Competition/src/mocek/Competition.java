package mocek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Competition {

    private String name;
    private ArrayList<Racer> racers;

    // ------------------------------------- SOUBORY -------------------------------------
    
    public void loadStart(File startFile) throws FileNotFoundException, IOException {
        // Dostáváme se do světa povinně ošetřovatelných výjimek
        // Soubor je vždy potřeba uzavřít, i když je výjimka, toho se dá docílit pomocí try+finally nebo "try with resources"
        // Try with resources – parametr pro try BufferedReader se vždy zavře
        try (BufferedReader br = new BufferedReader(new FileReader(startFile))) {
            String line;
            br.readLine(); // Přeskočíme první řádek (tam je hlavička)

            String firstName, lastName;
            int dob;
            char gender;
            String[] parts;
            Racer r;
            Gender g;

            while ((line = br.readLine()) != null) {
                parts = line.split("[ ]+"); // Hledáme jednu nebo více mezer
                firstName = parts[0];
                lastName = parts[1];
                dob = Integer.parseInt(parts[2]);
                gender = parts[3].charAt(0);
                g = (gender == 'M') ? Gender.male : Gender.female;
                r = new Racer(firstName, lastName, dob, g);
                racers.add(r);
            }
        }
    }

    public void saveToFile(File results) throws IOException {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(results)))) {
            sortByRunTime();
            int n = 1;
            for (Racer rcr : racers) {
                pw.print(n + ". ");
                pw.println(rcr.toString());
                n++;
            }
        }
    }

    public void saveToBinary(File results) throws FileNotFoundException, IOException {
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(results))) {
            sortByRunTime();
            out.writeInt(racers.size());
            for (Racer rcr : racers) {
                out.writeUTF(rcr.getForename());
                out.writeInt(rcr.getSurname().length());
                //out.writeUTF(rcr.getSurname());
                // Po znacích
                for (int i = 0; i < rcr.getSurname().length(); i++) {
                    out.writeChar(rcr.getSurname().charAt(i));
                }
                out.writeLong(rcr.runTime());
            }
        }
    }

    public String readBinaryResults(File results) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        try(DataInputStream in = new DataInputStream(new FileInputStream(results))) {
            boolean end = false;
            int rank = 1;
            while (!end) {
                try {
                    int nRunners = in.readInt();
                    for (int i = 0; i < nRunners; i++) {
                        sb.append(rank++).append(". ");
                        String forename = in.readUTF();
                        int nLetters = in.readInt();
                        String surname = "";
                        for (int j = 0; j < nLetters; j++) {
                            surname += in.readChar();
                        }
                        long time = in.readLong();
                        sb.append(String.format("%s %s %d %d%n", forename, surname, time, rank));
                    }
                }
                catch (EOFException ex) {
                    end = true;
                }
            }
        }
        return sb.toString();
    }

    public void loadFinish(File finishFile) throws FileNotFoundException {
        try (Scanner sc = new Scanner(finishFile)) {
            sc.nextLine(); // Přeskočíme hlavičku

            int number;
            String finishTime;;

            while(sc.hasNext()) {
                number = sc.nextInt();
                finishTime = sc.next();
                findRacerByNumber(number).setFinishTime(finishTime);
            }
        }
    }

    // -----------------------------------------------------------------------------------

    public Competition(String name) {
        this.name = name;
        racers = new ArrayList<>();
    }

    // Registrace nového závodníka
    public void registerRacer(String forename, String surname, int birthYear, Gender racerGender) {
        Racer rcr = new Racer(forename, surname, birthYear, racerGender);
        rcr.setCompetition(this);
        racers.add(rcr);
    }

    // Nastavení startovního/cílového času
    public void racerStart(int registrationNumber, String time) {
        Racer rcr = findRacerByNumber(registrationNumber);
        rcr.setStartTime(time);
    }

    public void racerStart(int registrationNumber, int hours, int minutes, int seconds) {
        Racer rcr = findRacerByNumber(registrationNumber);
        rcr.setStartTime(hours, minutes, seconds);
    }

    public void racerFinish(int registrationNumber, String time) {
        Racer rcr = findRacerByNumber(registrationNumber);
        rcr.setFinishTime(time);
    }

    public void racerFinish(int registrationNumber, int hours, int minutes, int seconds) {
        Racer rcr = findRacerByNumber(registrationNumber);
        rcr.setFinishTime(hours, minutes, seconds);
    }

    // Pomocná metoda pro nalezení závodníka
    private Racer findRacerByNumber(int registrationNumber) {
        Racer rtrn = null;
        for (Racer rcr : racers) {
            if (registrationNumber == rcr.getRegistrationNumber()) {
                rtrn = rcr;
                break;
            }
        }
        if (rtrn == null) throw new NoSuchElementException("Závodník s tímto číslem neexistuje");
        return rtrn;
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%s:\n", name));
        for (Racer rcr : racers) {
            sb.append(rcr.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    // Sortin'
    // Setříděno dle příjmení (využívá compareTo ve třídě Racer)
    public ArrayList<Racer> racersSortedBySurname() {
        ArrayList<Racer> list = new ArrayList<>(racers);
        Collections.sort(list);
        return list;
    }
    // Předdefinovaný komparátor
    public static final Collator col = Collator.getInstance(new Locale("cs","cz"));
    public static final Comparator<Racer> COMP_BY_NAME = (Racer r1, Racer r2) -> {
        int value = col.compare(r1.getSurname(), r2.getSurname());
        if (value == 0) {
            value = col.compare(r1.getForename(), r2.getForename());
        }
        return value;
    };

    public void sortBySurnameConst() {
        Collections.sort(racers, COMP_BY_NAME);
    }

    // Střiď podle runTime
    // - Třída Comparator
    public void sortByRunTime() {
        Collections.sort(racers, new ComparatorRacerByRunTime());
    }

    // - Anonymní vnitřní třída
    public void sortByRunTimeAnonymous() {
        Collections.sort(racers, new Comparator<Racer>(){
            @Override
            public int compare(Racer o1, Racer o2) {
                return Long.compare(o1.runTime(), o2.runTime());
            }
        });
    }
    // - Lambda výraz
    public void sortByRunTimeLambda() {
        Collections.sort(racers, (Racer o1, Racer o2) -> Long.compare(o1.runTime(), o2.runTime()));
    }

}
