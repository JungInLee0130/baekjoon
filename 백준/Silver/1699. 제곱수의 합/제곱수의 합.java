import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int count = 0;
        // ㅅㅂ 또 dp야
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                if (dp[i] > dp[i - j * j] + 1) {
                    dp[i] = dp[i - j * j] + 1;
                }
            }
        }

        // dp[1] = 1;
        // dp[4] = 1;
        // dp[9] = 1; -> 제곱수들은 모두 1개이다.
        // dp[8] = dp[7] + 1 or dp[4] + 1 중에 하나일 것이다.
        // N보다 작은 제곱수들로 빼준것 + 1 중에 더 작은게 있으면 그걸로 대체한다.


        System.out.println(dp[N]);
    }
}