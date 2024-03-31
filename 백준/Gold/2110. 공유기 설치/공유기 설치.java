import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N <= 20만
        C = Integer.parseInt(st.nextToken()); // 2개이상 N 이하

        x = new int[N]; // x[i] <= 10억
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(x);

        bs();
    }

    private static void bs() {
        int result = 0;

        int low = 1; // 최소간격
        int high = x[N - 1] - x[0]; // 최대간격

        while (low <= high) {
            int cnt = 1; // 맨 처음에 둠
            int pos = 0;

            int mid = (low + high) / 2;

            for (int i = 1; i < N; i++) {
                if (x[i] - x[pos] >= mid) {
                    cnt++;
                    pos = i;
                }
            }

            if (cnt >= C) { // 공유기 이상이거나 같으면
                result = mid;
                low = mid + 1; // 간격늘리기
            }
            else{ // 적으면
                high = mid - 1; // 간격좁히기
            }
            //System.out.println(low+ "," + high);
        }

        System.out.println(low - 1);
    }

    static int[] x;
    static int[] spot;
    static int maxLen = 0;
}