import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static long[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new long[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        long[] preSum = new long[N + 1];

        for (int i = 1; i <= N; i++){
            preSum[i] = preSum[i - 1] + A[i];
        }

        long maxValue = 0;
        for (int i = M; i <= N; i++) {
            long value = preSum[i] - preSum[i - M];
            if (maxValue < value){
                maxValue = value;
            }
        }

        System.out.println(maxValue);

        br.close();
    }
}