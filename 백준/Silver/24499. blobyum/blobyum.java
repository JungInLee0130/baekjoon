import java.io.*;
import java.util.*;

class Main {
    static int N, K;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long max = 0;
        long sum = 0;
        int idx = 0;

        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }

        idx++;

        max = sum;

        while (true) {
            if ((K - 1 + idx) % N == K - 1) {
                break;
            }
            sum -= arr[(idx - 1) % N];
            sum += arr[(K - 1 + idx) % N];
            if (max < sum) {
                max = sum;
            }
            idx++;
        }

        System.out.println(max);

        br.close();
    }
}