import java.io.*;
import java.util.*;

public class Main {
    static int N, T;
    static int[][] liquid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        liquid = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            liquid[i][0] = Integer.parseInt(st.nextToken());
            liquid[i][1] = Integer.parseInt(st.nextToken());
        }

        int answer = binarySearch();

        System.out.println(answer);
    }

    private static int binarySearch() {
        int answer = -1;

        int left = 1;
        int right = T;
        while (left <= right) {
            int mid = (left + right) >> 1;

            // 1. mid가 Li <= mid <= Ri에 속하는지
            boolean firstCondition = isRange(mid);

            if (!firstCondition){
                left = mid + 1;
                continue;
            }

            // 2. 합이 T가 나올수있는지
            int sum = getSum(mid);

            if (sum == T){
                answer = mid;
                right = mid - 1;
            } else if (sum > T) {
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }

        if (answer == -1) {
            return -1;
        }

        return answer;
    }


    private static int getSum(int S) {
        int rsum = 0;
        for (int i = 0; i < N; i++) {
            // 범위안에있는거여야함.
            if (S >= liquid[i][1]) {
                rsum += liquid[i][1];
            } else if (liquid[i][0] <= S && S <= liquid[i][1]) {
                rsum += S;
            }
        }

        int lsum = 0;
        for (int i = 0; i < N; i++) {
            lsum += liquid[i][0];
        }

        if (lsum > T){
            return lsum;
        }
        else if (lsum <= T && T <= rsum){
            return T; // T가능
        }
        // T > rsum
        else{
            return rsum;
        }
    }

    private static boolean isRange(int S) {
        for (int i = 0; i < N; i++) {
            if (S < liquid[i][0]) return false;
        }
        return true;
    }
}