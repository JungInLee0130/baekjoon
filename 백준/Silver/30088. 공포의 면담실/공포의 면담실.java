import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] times;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        times = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M; j++) {
                times[i] += Integer.parseInt(st.nextToken()); // 누적합
            }
        }

        Arrays.sort(times);

        int[] preSum = new int[N];
        preSum[0] = times[0];

        for (int i = 1; i < N; i++) {
            preSum[i] = preSum[i - 1] + times[i];
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += preSum[i];
        }

        System.out.println(sum);

        br.close();
    }
}