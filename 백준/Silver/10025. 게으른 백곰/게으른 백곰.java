import java.io.*;
import java.util.*;

class Main {
    static int N, K;
    static int[] G;
    static final int MAX_SIZE = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int max = 0;
        G = new int[MAX_SIZE + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            G[x] = g;
            max = Math.max(max, x);
        }


        int sum = 0;

        // 중복되고 불편하고 같은거 그냥 묶어버림
        int MAX = 2 * K;
        // 0 ~ 1_000_000 // 범위가 문제. 잘지키셈.
        for (int i = 0; i <= Math.min(MAX, MAX_SIZE); i++) {
            sum += G[i];
        }

        int answer = sum;

        for (int i = 1; i <= MAX_SIZE - MAX; i++) {
            sum = sum - G[i - 1] + G[i + MAX];
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);

        br.close();
    }
}