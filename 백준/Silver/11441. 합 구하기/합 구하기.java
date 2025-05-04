import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++){
            preSum[i] = preSum[i - 1] + A[i - 1];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int sum = preSum[b] - preSum[a - 1];

            sb.append(sum + "\n");
        }

        System.out.print(sb);

        br.close();
    }
}