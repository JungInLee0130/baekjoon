import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static long[] fibo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 자릿수

        fibo = new long[N + 2];

        fibo[0] = 0;
        fibo[1] = 1;
        fibo[2] = 1;

        for (int i = 3; i <= N; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }


        bw.write(String.valueOf(fibo[N]));
        bw.flush();
        bw.close();
        br.close();
    }
}