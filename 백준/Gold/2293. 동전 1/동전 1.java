import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int n, k;
    static boolean[] isHas;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) { // 100
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1]; // 100까지
        dp[0] = 1;
        for (int j = arr[0]; j <= k; j++) {
            if (j % arr[0] == 0) {
                dp[j]++;
            }
        }


        // dp[k] = dp[k] + dp[k - arr[i]]
        for (int i = 1; i < n; i++) { // 동전개수
            for (int j = arr[i]; j <= k; j++) { // 1부터 k까지
                dp[j] += dp[j - arr[i]];
            }
        }

        System.out.println(dp[k]);
    }
}