import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static long[] liq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        liq = new long[N];
        for (int i = 0; i < N; i++) { // 5000
            liq[i] = Long.parseLong(st.nextToken()); // -10억 <= <= 10억
        }

        Arrays.sort(liq);

        min = Long.MAX_VALUE;
        ans = new long[3];
        for (int i = 0; i <= N - 3; i++) {
            if (min != 0) {
                binarySearch(i, i + 1, N - 1);
            }
        }

        Arrays.sort(ans);

        for (long e: ans) {
            System.out.print(e + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static long min;
    static long[] ans;

    private static void binarySearch(int idx, int start, int end) {
        // idx, idx + 1, end
        long mid;

        while (start < end) {
            mid = liq[idx] + liq[start] + liq[end];

            if (Math.abs(mid) < min) {
                min = Math.abs(mid);

                ans[0] = liq[idx];
                ans[1] = liq[start];
                ans[2] = liq[end];
            }

            if (mid > 0) {
                end--;
            } else if (mid == 0) {
                break;
            } else{
                start++;
            }
        }
        // 투포인터가 자연스럽게 되는데....
    }
}