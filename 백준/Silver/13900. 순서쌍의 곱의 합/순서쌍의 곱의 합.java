import java.io.*;
import java.util.*;

class Main {
    static int N;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long[] preSum = new long[N + 1];
        preSum[1] = arr[1];

        for (int i = 2; i <= N; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }

        long sum = 0;

        for (int i = 2; i <= N; i++) {
            sum += preSum[i - 1] * arr[i];
        }

        System.out.println(sum);

        br.close();
    }
}