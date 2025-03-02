import java.io.*;
import java.util.*;

class Main {
    static int N, K;
    static final int S = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        // nCk = n-1Ck-1 + n-1Ck
        // func : 재귀
        dp = new int[N + 1][K + 1];

        int res = func(N, K);

        /*for (int i = 0; i <= N; i++) { // 1000
            for (int j = 0; j <= K; j++) { // 1000
                if (i < j){
                    break;
                }
                if (i == j || j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (j == 1){
                    dp[i][j] = i;
                    continue;
                }
                if (i > j && i > 1 && j > 1){
                    dp[i][j] = (dp[i - 1][j - 1] % S + dp[i - 1][j] % S) % S;
                    continue;
                }
            }
        }*/

        //System.out.println(dp[N][K]);

        System.out.println(res);


        br.close();
    }

    static int[][] dp;
    private static int func(int n, int k) {
        if (dp[n][k] > 0) return dp[n][k];

        if (n == k || k == 0) {
            dp[n][k] = 1;
            return dp[n][k];
        }
        if (k == 1) {
            dp[n][k] = n;
            return dp[n][k];
        }
        return dp[n][k] = (func(n - 1, k - 1) % S + func(n - 1, k) % S) % S;
    }
}