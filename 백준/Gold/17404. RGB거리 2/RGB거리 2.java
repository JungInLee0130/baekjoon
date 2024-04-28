import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static int N;
    static int[][] price;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        price = new int[N + 1][4];

        // 순환
        // 1 -> N, 2번과 색같으면 안됨.

        // i -> i - 1 , i + 1 : x

        // 비용 최소
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                price[i][j] = Integer.parseInt(st.nextToken()); // 1000이하
            }
        }

        int[][] dp = new int[N + 1][4];

        // 26 57 89 조건이 2개 생기는듯.
        // 첫집을 칠하는 경우를 모두 나눠서 풀고, 3중에서 최소값을 구한다.
        int answer = Integer.MAX_VALUE;
        for (int k = 1; k <= 3; k++) {
            for (int j = 1; j <= 3; j++) {
                if (j == k) {
                    dp[1][j] = price[1][j];
                    continue;
                }
                dp[1][j] = 1001;
            }

            for (int i = 2; i <= N; i++) {
                dp[i][1] = Math.min(dp[i - 1][2], dp[i - 1][3]) + price[i][1];
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][3]) + price[i][2];
                dp[i][3] = Math.min(dp[i - 1][1], dp[i - 1][2]) + price[i][3];
            }


            for (int j = 1; j <= 3; j++) {
                if (k == j) {
                    continue;
                }
                //System.out.println(dp[N][j]);
                answer = Math.min(answer, dp[N][j]);
            }
        }

        System.out.println(answer);

        bw.flush();
        bw.close();
        br.close();
    }
}