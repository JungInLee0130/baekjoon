import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 1억

        // 1 ~ 1억 이어붙이는 자릿수
        String s = String.valueOf(N);
        int len = s.length();

        long result = (long) ((N - Math.pow(10, len - 1) + 1) * len);;

        for (int i = len - 2; i >= 0; i--) {
            result += 9 * Math.pow(10, i) * (i + 1);
        }

        System.out.println(result);
        bw.flush();
        bw.close();
        br.close();
    }
}