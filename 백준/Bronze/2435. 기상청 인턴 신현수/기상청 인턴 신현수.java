import java.io.*;
import java.util.*;

class Main {
    static int[] A;
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // K일의 온도의 합이 최대가되는 값 출력
        int[] answer = new int[N - K + 1];

        for (int i = 0; i < K; i++) {
            answer[0] += A[i];
        }

        for (int i = 1; i <= N - K; i++) {
            answer[i] = answer[i - 1] - A[i - 1] + A[K - 1 + i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= N - K; i++) {
            max = Math.max(max, answer[i]);
        }

        System.out.println(max);

        br.close();
    }
}