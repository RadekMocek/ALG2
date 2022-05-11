package pkg06_banka;

import java.io.File;
import java.io.IOException;

public class Ucet {

    private String accNum;
    private File file;

    public Ucet(String accNum) throws IOException {
        this.accNum = accNum;
        file = new File(accNum + ".dat");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void Insert(Transakce t) {

    }

}
