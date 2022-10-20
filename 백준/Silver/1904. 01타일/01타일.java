import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 00, 1
        int[] dp = new int[N + 1]; // 1~N
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746; // i >= 2
        }

        bw.write(String.valueOf(dp[N]));
        // 와... 범위 -> int -> 매우 커질수있음. -> 더할때마다 나눠주기

        bw.flush();
        br.close();
        bw.close();
    }
}
