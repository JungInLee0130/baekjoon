import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static long N, M;
    static long[] time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken()); // 10만 이하
        M = Long.parseLong(st.nextToken()); // 10억 이하

        time = new long[(int) N];
        for (int i = 0; i < N; i++) {
            time[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(time);

        binarySearch();

        System.out.println(answer);

        bw.flush();
        br.close();
        bw.close();
    }

    private static long answer;
    private static void binarySearch() {
        long left = 0;
        long right = M * time[(int) (N - 1)]; // 최대 걸리는 시간

        while (left <= right) {
            long mid = (left + right) / 2;

            long count = 0;
            for (long e : time) {
                count += mid / e;
                if (count >= M) {
                    break;
                }
            }

            if (count >= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        answer = Math.max(left, right);
    }
}