import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        long[] num = new long[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            num[i] = Long.parseLong(st.nextToken());
        }

        long[] dp = new long[N + 1]; // 1 ~ N
        long max = -1000;
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = max(dp[i - 1] + num[i], num[i]);
        }

        for (int i = 1; i <= N; i++) {
            max = max(dp[i], max);
        }

        bw.write(String.valueOf(max));

        bw.flush();
        br.close();
        bw.close();
    }
}
