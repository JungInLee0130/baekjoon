import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] A;
    static int[] result;
    static final int max_X = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        result = new int[N]; // 우선 N개로 정한다.

        // 우선 첫번째 원소를 넣는다.

        result[0] = A[0];

        len = 1;
        for (int i = 1; i < N; i++) {
            binarySearch(i);
        }

        System.out.println(len);
        /*for (int i = 0; i < len; i++) {
            System.out.print(result[i] + " ");
        }*/


        bw.flush();
        br.close();
        bw.close();
    }

    static int len;
    private static void binarySearch(int idx) {
        int left = 0;
        int right = len - 1; // 끝 인덱스

        // 지금 들어있는 원소보다 큰원소임
        if (result[len - 1] < A[idx]) {
            result[len] = A[idx];
            len++;
            return;
        }

        // 더 작거나 같음 : 위치 찾아주고 대신 넣어줌.
        while (left <= right) {
            int mid = (left + right) >> 1;

            if (left == right) {
                result[mid] = A[idx]; // 교체
                return;
            }

            if (result[mid] >= A[idx]) {
                right = mid;
            } else if (result[mid] < A[idx]) {
                left = mid + 1;
            }
        }
    }
}