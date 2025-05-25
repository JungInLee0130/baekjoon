import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] A;
    static long[] preSum;
    static final long DIVIDE_NUM = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        preSum = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + A[i];
        }

        long result = 0;
        for (int i = 2; i <= N; i++) {
            result = result % DIVIDE_NUM + (preSum[i - 1] % DIVIDE_NUM * A[i]) % DIVIDE_NUM;
            result = result % DIVIDE_NUM;
        }

        System.out.println(result);

        br.close();
    }
}