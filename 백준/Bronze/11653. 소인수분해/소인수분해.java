import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 10^7

        // 이미 소수가 걸러지니까 소수 검사 필요없음.
        for (int i = 2; i <= N; i++) { // 10^7
            while (N % i == 0) {
                N = N / i;
                if (N == 0) {
                    return;
                }
                System.out.println(i);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}