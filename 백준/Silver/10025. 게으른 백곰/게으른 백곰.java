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

        int MAX = 2 * K;

        for (int i = 0; i <= Math.min(MAX, 1_000_000); i++) {
            sum += G[i];
        }

        int answer = sum;

        int SIZE = 2 * K;

        for (int i = 1; i <= 1_000_000 - SIZE; i++) {
            sum -= G[i - 1];
            sum += G[i + SIZE];
            if (i + SIZE > max) break;
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);

        br.close();
    }
}