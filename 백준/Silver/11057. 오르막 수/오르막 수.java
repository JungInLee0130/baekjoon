import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int len;
    static final int MAX_LEN = 1001;
    static long[][] dp = new long[MAX_LEN][MAX_LEN];
    static long[] sum = new long[MAX_LEN];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        len = Integer.parseInt(br.readLine()); // 1000

        sum[1] = 10;
        sum[2] = 55;

        int k = 0;
        for (int c = 1; c <= 10; c++) {
            dp[2][c] = 10 - k;
            k++;
        }

        // len까지
        for (int i = 3; i <= len; i++) {
            for (int j = 1; j <= 10; j++) {
                if (j == 1) {
                    dp[i][j] = sum[i - 1]; // 그전 총합
                    continue;
                }

                dp[i][j] = (dp[i][j - 1] - dp[i - 1][j - 1]) % 10007; // 다 수를 넣고 10007의 나머지를 구해줌
                if (dp[i][j] < 0) {
                    dp[i][j] += 10007;
                }
            }

            // 모두 대입이 끝나면 총합을 구함
            for (int j = 1; j <= 10; j++) {
                sum[i] += dp[i][j];
                sum[i] %= 10007;
            }
        }

        /*for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }*/


        System.out.println(sum[len] % 10007);


        bw.flush();
        br.close();
        bw.close();
    }
}