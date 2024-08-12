import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long K;
    static long[] levels;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        levels = new long[N];
        long left = 0;
        long right = 0;
        for (int i = 0; i < N; i++) {
            levels[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(levels);

        left = levels[0];
        right = levels[N - 1] + K;

        long answer = sol(left,right);

        System.out.println(answer);


        bw.flush();
        br.close();
        bw.close();
    }

    private static long sol(long left, long right) {
        return binarySearch(left, right);
    }

    private static long binarySearch(long left, long right) {
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (getK(mid) > K) {
                right = mid - 1;
            }
            else{
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;
    }

    private static long getK(long mid) {
        long value = 0;
        for (int i = 0; i < N; i++) {
            if (mid > levels[i]) {
                value += mid - levels[i];
            }
        }
        return value;
    }
}