import java.io.*;
import java.util.*;

class Main {
    static int T, N;
    static long[] X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            X = new long[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                X[i] = Long.parseLong(st.nextToken());
            }
            long[] preSum = new long[N];
            preSum[0] = X[0];
            for (int i = 1; i < N; i++) {
                preSum[i] = preSum[i - 1] + X[i];
            }

            long max = -1_000_000;
            for (int i = 0; i < N; i++) {
                max = Math.max(max, preSum[i]);
            }

            for (int i = 1; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    long sub = preSum[i] - preSum[j];
                    if (sub > max) {
                        max = sub;
                    }
                }
            }

            sb.append(max + "\n");
        }

        System.out.print(sb);

        br.close();
    }
}