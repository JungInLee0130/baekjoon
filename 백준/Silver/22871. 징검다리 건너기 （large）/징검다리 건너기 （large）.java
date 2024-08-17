import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 5000이하

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Long.parseLong(st.nextToken()); // 100만 이하
        }

        long[] dp = new long[5001];
        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            dp[i] = Long.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                // dp[i] : 1에서 i까지 오는 힘
                // dp[j] : 1에서 j까지 오는 힘
                // getValue(j,i) : j에서 i까지 가는 힘
                // dp[2] = Math.min(dp[i], Math.max(dp[1], getValue(2,1)))
                dp[i] = Math.min(dp[i], Math.max(dp[j], getValue(j, i)));
            }
        }

        System.out.println(dp[N]);

        bw.flush();
        br.close();
        bw.close();
    }

    // j < i
    private static long getValue(int j, int i) {
        return (i - j) * (Math.abs(A[i] - A[j]) + 1);
    }
}