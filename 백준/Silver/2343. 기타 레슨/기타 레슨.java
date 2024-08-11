import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dvs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dvs = new int[N];
        int start = 0;
        int end = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dvs[i] = Integer.parseInt(st.nextToken());
            if (start < dvs[i]) start = dvs[i]; // 가장 큰값을 가진 dv
            end = end + dvs[i]; // 길이의 총 합
        }

        binarySearch(start, end);

        System.out.println(answer);


        bw.flush();
        br.close();
        bw.close();
    }

    private static int answer;
    private static void binarySearch(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            // 불가 : 더 늘려야함.
            int count = getCount(mid);
            // M보다 수가 크면안됨 : 기준 늘려줌
            if (count > M) left = mid + 1;
            // 조금 더 줄여봄
            else right = mid - 1;
        }
        answer = Math.max(left, right);
    }

    // M개가 되어야함.
    private static int getCount(int mid) {
        int sum = 0;
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (sum + dvs[i] > mid) {
                count++; // count 늘리고, sum 초기화
                sum = 0;
            }
            sum += dvs[i];
        }

        if (sum != 0) count++; // 남은거있으면 count + 1
        return count;
    }
}