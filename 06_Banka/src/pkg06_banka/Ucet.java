package pkg06_banka;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ucet {

    private int accNum;

    private File file;

    public Ucet(int accNum) {
        this.accNum = accNum;
        associateWithFile();
    }

    public int getAccNum() {
        return accNum;
    }

    private void associateWithFile() {
        File temp = new File("data" + File.separator + accNum + ".txt");
        file = temp;
    }

    public void depositWithdraw(int day, int month, int year, boolean depositWithdraw, int sum) throws FileNotFoundException, IOException {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file, true)))) {
            out.writeInt(day);
            out.writeInt(month);
            out.writeInt(year);
            out.writeBoolean(depositWithdraw);
            out.writeInt(sum);
        }
    }

    public String printout() throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            boolean end = false;
            while(!end) {
                try {
                    int day = in.readInt();
                    int month = in.readInt();
                    int year = in.readInt();
                    boolean depositWithdraw = in.readBoolean();
                    int sum = in.readInt();
                    sb.append(String.format("%d-%d-%d ", year, month, day));
                    if (depositWithdraw) sb.append("+"); else sb.append("-");
                    sb.append(sum);
                    sb.append(" CZK \n");
                }
                catch(EOFException ex) {
                    end = true;
                }
            }
        }
        return sb.toString();
    }

}
