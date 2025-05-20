import java.io.*;
import java.util.*;

class Main {
    static int N, Q;
    static int[] A, B;
    static long[] preSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        A = new int[N];
        B = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        B = Arrays.copyOf(A, N);

        Arrays.sort(B);

        preSum = new long[N];

        preSum[0] = B[0];

        for (int i = 1; i < N; i++) {
            preSum[i] = preSum[i - 1] + B[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken()) - 1;
            int R = Integer.parseInt(st.nextToken()) - 1;

            long sub = preSum[R];
            if (L == 0) {

            } else {
                sub -= preSum[L - 1];
            }

            sb.append(sub + "\n");
        }

        System.out.print(sb);

        br.close();
    }
}