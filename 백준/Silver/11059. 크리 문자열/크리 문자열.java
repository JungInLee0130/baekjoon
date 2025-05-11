import java.io.*;
import java.util.*;

class Main {
    static String S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();

        int N = S.length();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = S.charAt(i) - '0';
        }

        int[] preSum = new int[N];

        preSum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            preSum[i] = arr[i] + preSum[i - 1];
        }

        System.out.println(findCriStr(N, preSum));

        br.close();
    }

    private static int findCriStr(int N, int[] preSum) {
        int len = N;
        if (N % 2 != 0) {
            len -= 1;
        }

        int result = 0;

        while (len >= 2) {
            for (int idx = 0; idx <= N - len; idx++) {
                int left = preSum[len / 2 - 1 + idx];
                if (idx == 0) {

                }else {
                    left = left - preSum[idx - 1];
                }

                int right = preSum[len - 1 + idx] - preSum[len / 2 - 1 + idx];

                if (left == right) {
                    result = len;
                    return result;
                }
            }
            len -= 2;
        }
        return -1;
    }
}