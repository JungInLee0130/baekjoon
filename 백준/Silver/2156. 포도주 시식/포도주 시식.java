import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int[] grape = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            grape[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = grape[1];
        if (N >= 2) {
            dp[2] = grape[1] + grape[2];
            for (int i = 3; i <= N; i++) {
                dp[i] = Math.max(Math.max(dp[i - 1], dp[i - 2] + grape[i]), dp[i - 3] + grape[i - 1] + grape[i]);
            }
        }


        bw.write(String.valueOf(dp[N]));

        bw.flush();
        br.close();
        bw.close();
    }
}
