import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int[] levels;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        levels = new int[N];
        int left = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            levels[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(levels);

        left = levels[0];
        right = levels[N - 1] + K;

        int answer = sol(left,right);

        System.out.println(answer);


        bw.flush();
        br.close();
        bw.close();
    }

    private static int sol(int left, int right) {
        return binarySearch(left, right);
    }

    private static int binarySearch(int left, int right) {
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

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

    private static long getK(int mid) {
        long value = 0;
        for (int i = 0; i < N; i++) {
            if (mid > levels[i]) {
                value += mid - levels[i];
            }
        }
        return value;
    }
}