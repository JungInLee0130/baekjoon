import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static boolean[] isYes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        isYes = new boolean[31];

        for (int i = 0; i < 28; i++) {
            isYes[Integer.parseInt(br.readLine())] = true;
        }

        for (int i = 1; i <= 30; i++) {
            if (!isYes[i]) {
                System.out.println(i);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}