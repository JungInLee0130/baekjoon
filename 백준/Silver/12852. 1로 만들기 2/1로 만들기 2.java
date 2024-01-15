import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());


        int[] before = new int[N + 1]; // 1 ~ N
        dp = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            before[i] = i - 1;
            
            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                before[i] = i / 3;
            } 
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                before[i] = i / 2;
                dp[i] = dp[i / 2] + 1;
            }
        }

        System.out.println(dp[N]);

        ArrayList<Integer> arrayList = new ArrayList<>();

        int s = N;

        while (true) {
            arrayList.add(s);

            if (s == 1) break;
            s = before[s];
        }

        for (Integer e : arrayList) {
            System.out.print(e + " ");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}