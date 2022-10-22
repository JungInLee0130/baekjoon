import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] num = new int[N + 1][N + 1]; // 1부터 N까지 사용
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + num[i][j];
            }
        }

        int result = 0;

        for (int i = 1; i <= N; i++) {
            result = max(dp[N][i], result);
        }

        bw.write(String.valueOf(result));

        bw.flush();
        br.close();
        bw.close();
    }
}
