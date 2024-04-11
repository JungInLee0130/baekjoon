import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] dp;
    static String[] str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 2000

        StringTokenizer st = new StringTokenizer(br.readLine());
        str = new String[N + 1]; // 1 ~ N
        str[0] = "0";
        for (int i = 1; i <= N; i++) {
            str[i] = st.nextToken();
        }

        M = Integer.parseInt(br.readLine()); // 100만개

        dp = new boolean[N + 1][N + 1];

        solve();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (dp[start][end]) {
                bw.write("1\n");
            }
            else{
                bw.write("0\n");
            }
        }

        // 이게 dp지
        //System.out.println(str[1] == str[3]);
        //System.out.println(str[1].equals(str[3]));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void solve() {
        // 1
        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        // 2
        for (int i = 1; i <= N - 1; i++) {
            if (str[i].equals(str[i + 1])) dp[i][i + 1] = true;
        }

        // 3
        for (int i = 2; i < N; i++) { // i + 1 ~ n 길이 (인덱스 기준)
            for (int j = 1; j <= (N - i); j++) { // 길이에따른 범위
                // 처음 == 끝 (양 끝점 비교)
                // 처음 + 1 ~ == 끝 - 1 ~ (저장된 dp 사용)
                if (str[j].equals(str[j + i]) && dp[(j) + 1][(j + i) - 1]) {
                    dp[j][j + i] = true;
                }
            }
        }
    }
}