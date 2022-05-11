package pkg06_banka;

import java.io.File;
import java.time.LocalDate;

public class Transakce {

    /*
    private int year;
    private int month;
    private int day;
    */
    private LocalDate date;
    private int sum;

    public Transakce(/*int year, int month, int day,*/ int sum) {
        /*
        this.year = year;
        this.month = month;
        this.day = day;
        */
        this.date = LocalDate.now();
        this.sum = sum;
    }

    void saveToFile(File file) {
        long nDays = date.toEpochDay();
        //try w/ resources dataoutputstream 😣
    }

}
