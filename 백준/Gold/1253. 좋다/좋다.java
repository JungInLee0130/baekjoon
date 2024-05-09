import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 2000개
            A[i] = Integer.parseInt(st.nextToken()); // 절댓값 10억 이하
        }

        // 정렬
        Arrays.sort(A);

        // 투포인터
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (twoPointer(i)) {
                ans++;
            }
        }

        bw.write(String.valueOf(ans));



        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean twoPointer(int idx) {
        int left = 0;
        int right = N - 1;
        while (left < right) {
            if (left == idx) left++;
            if (right == idx) right--;

            if (left == right) {
                return false;
            }

            int sum = A[left] + A[right];

            if (sum > A[idx]) right--;
            else if (sum < A[idx]) left++;
            else{
                //System.out.println(A[idx] + " " + left + " " + right);
                return true;
            }
        }
        return false;
    }
}