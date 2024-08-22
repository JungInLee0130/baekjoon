import java.io.*;
import java.util.*;

public class Main {
    static int N, S, M;
    static int[] seconds;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        S = N - S; // left

        M = Integer.parseInt(br.readLine());

        seconds = new int[M];
        for (int i = 0; i < M; i++) { // 10만이하
            seconds[i] = Integer.parseInt(br.readLine()); // 1000이하
        }

        int time = binarySearch(0, S);

        cal(time);

        // -1 나오는 경우는 없음.
        System.out.println(index);

    }

    private static int index;

    private static void cal(int time) {
        int t = 0;
        while (S > 0) {
            for (int i = 0; i < M; i++) {
                if (t % seconds[i] == 0) {
                    S--;
                    if (S == 0) {
                        index = i + 1;
                        return;
                    }
                }
            }
            t++;
        }
    }

    private static int binarySearch(int left, int right) {
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) >> 1;

            int count = getCount(mid);

            if (count >= S) {
                answer = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return answer; // 1~M
    }

    private static int getCount(int T) {
        int count = M;
        for (int i = 0; i < M; i++) {
            count += T / seconds[i];
        }

        return count;
    }
}