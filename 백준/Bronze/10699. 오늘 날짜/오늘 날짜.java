import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static long A, B;
    static ArrayList<Integer> xAxis;
    static ArrayList<Integer> yAxis;
    static ArrayList<Integer> zAxis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        System.out.println(LocalDate.now());

        bw.flush();
        bw.close();
        br.close();
    }
}