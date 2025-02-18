import java.io.*;
import java.util.*;

class Main {
    static StringTokenizer st;
    static int N, K;
    static long[] liquid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        liquid = new long[N];
        for (int i = 0; i < N; i++) {
            liquid[i] = Long.parseLong(br.readLine());
        }

        // K가 최대

        long low = 0;
        long high = Integer.MAX_VALUE - 1;

        long answer = 0;
        while (low <= high) {
            long mid = (low + high) / 2;

            long count = getCount(mid);

            if (count < K) {
                high = mid - 1;
            } else if (count >= K) {
                answer = mid;
                low = mid + 1;
            }
        }

        System.out.println(answer);
        //System.out.println((int)Math.pow(2,31));

        br.close();
    }

    private static long getCount(long mid) {
        long count = 0;

        if (mid == 0) {
            return K;
        }

        for (int i = 0; i < N; i++) {
            if (liquid[i] == 0) {
                continue;
            }
            count += liquid[i] / mid;
        }

        return count;
    }
}