import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        // 10 -> B진법

        StringBuilder sb = new StringBuilder();
        while (true) {
            int q = N / B;
            int rest = N % B;

            if (N == 0) {
                break;
            }

            sb.append(convert(rest));

            N = q; // 몫
        }

        System.out.println(sb.reverse());

        bw.flush();
        br.close();
        bw.close();
    }

    private static String convert(int rest) {
        if (0 <= rest && rest <= 9) {
            return String.valueOf(rest);
        }
        else{ // 10이상
            return String.valueOf((char)('A' + rest - 10));
        }
    }
}