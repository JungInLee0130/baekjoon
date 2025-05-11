import java.io.*;
import java.util.*;

class Main {
    static int N, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] x = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }

        int[] preSum = new int[N];
        preSum[0] = x[0];

        for (int i = 1; i < N; i++) {
            preSum[i] = x[i] + preSum[i - 1];
        }

        long result = 0;

        for (int i = 0; i < N - 1; i++){
            result += x[i] * (preSum[N - 1] - preSum[i]);
        }

        System.out.println(result);

        br.close();
    }
}