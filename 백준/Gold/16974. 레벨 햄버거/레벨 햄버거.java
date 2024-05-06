import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static long X;
    static long[] total, pat;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());

        total = new long[N + 1]; // 총 길이
        pat = new long[N + 1]; // 패티수

        total[0] = 1;
        pat[0] = 1;

        for (int i = 1; i <= N; i++) {
            total[i] = 1 + total[i - 1] + 1 + total[i - 1] + 1;
            pat[i] = pat[i - 1] + 1 + pat[i - 1];
        }

        long ans = recursion(N, X);

        System.out.println(ans);


        bw.flush();
        bw.close();
        br.close();
    }

    private static long recursion(int n, long x) {
        if (n == 0) {
            if (x == 0) {
                return 0;
            } else if (x == 1) {
                return 1;
            }
        }

        if (x == 1) {
            return 0;
        } else if (x <= 1 + total[n - 1]) {
            return recursion(n - 1, x - 1);
        } else if (x == 1 + total[n - 1] + 1) {
            return pat[n - 1] + 1;
        } else if (x <= 1 + total[n - 1] + 1 + total[n - 1]) {
            return pat[n - 1] + 1 + recursion(n - 1, x - (1 + total[n - 1] + 1));
        } else {
            return pat[n - 1] + 1 + pat[n - 1];
        }
    }
}