import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        int left = 0;
        int right = K;

        while (left < right) {
            int mid = (left + right) / 2;

            int cnt = 0;

            for (int i = 1; i <= N; i++) {
                cnt += Math.min(N, mid / i); // 몇번째 수인지 구함
            }

            if (cnt >= K) {
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        System.out.println(right);
    }
}