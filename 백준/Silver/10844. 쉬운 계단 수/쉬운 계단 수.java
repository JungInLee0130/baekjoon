import java.io.*;
import java.math.BigInteger;

public class Main {
    static int N;
    static int mod = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][10]; // 1 ~ N / 0 ~ 9

        for (int i = 1; i <= 9; i++) { // 한자리수 : 1 ~ 9
            dp[1][i] = 1;
        }

        if (N >= 2) {
            for (int i = 2; i <= N; i++) { // 두자리수 : 0 ~ 9
                dp[i][0] = dp[i - 1][1] % mod; // [1][1]
                for (int j = 1; j <= 8; j++) { // 1 ~ 8 : 0 ~ 9
                    // dp[2][1] = dp[1][0] + dp[1][2]
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                }
                dp[i][9] = dp[i - 1][8] % mod;
            }
        }

        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[N][i] % mod;
        }

        bw.write(String.valueOf(result % mod));

        bw.flush();
        br.close();
        bw.close();
    }
}
