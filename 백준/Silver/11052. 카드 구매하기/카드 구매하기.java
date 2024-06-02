import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    static int[] P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        P = new int[N + 1];
        for (int i = 1; i <= N; i++) { // 1000
            P[i] = Integer.parseInt(st.nextToken()); // 10000
        }

        // 1 5 6 7

        // P[2] = Math.max(P[1] + P[1], P[2])
        //
        // 1 1 1 1
        // 2 1 1
        // 2 2
        // 3 1
        // 4
        // N개를 같기위한 최댓값
        int[] dp = new int[N + 2];
        /*dp[1] = P[1];
        dp[2] = Math.max(P[1] + P[1], P[2]);
        dp[3] = Math.max(dp[2] + dp[1], P[3]);*/
        // dp[4] = Math.max(dp[3] + P[1], dp[2] + dp[2], P[4]);
        // dp[5] = Math.max(dp[5], dp[4] + P[1], dp[3] + dp[2] )
        // dp[i] = P[i];
        // dp[i] = Math.max(dp[i], dp[i - 1] + P[1])
        // dp[i] = Math.max(dp[i], dp[i - 2] + P[2])
        // dp[i] = Math.max(dp[i], dp[i - N / 2] + P[N / 2])
        for (int i = 1; i <= N; i++) {
            dp[i] = P[i];
        }

        // dp[2] = Math.max(dp[2], dp[1] + P[1])
        // dp[3] = Math.max(dp[3], dp[2] + P[1])
        // dp[4] = Math.max(dp[4], dp[3] + P[1])
        // dp[4] = Math.max(dp[4], dp[2] + P[2])
        for (int i = 1; i <= N; i++) { // 1000
            for (int j = 1; j <= i / 2; j++) { // 500
                dp[i] = Math.max(dp[i], dp[i - j] + P[j]);
            }
        }

        System.out.println(dp[N]);
    }
}