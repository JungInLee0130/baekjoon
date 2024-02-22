import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] liquid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        liquid = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        answer = Long.MAX_VALUE;
        bs();
    }

    static long answer;
    private static void bs() {
        int ml = 0;
        int mr = 0;

        for (int i = 0; i < N - 1; i++) {
            int low = i + 1;
            int high = N - 1;

            while (low <= high) {
                int mid = (low + high) / 2;

                long sum = liquid[mid] + liquid[i];
                if (Math.abs(answer) > Math.abs(sum)) {
                    answer = sum;
                    ml = i; mr = mid;
                }

                if (liquid[mid] >= -liquid[i]) {
                    high = mid - 1;
                } else if (liquid[mid] < -liquid[i]) {
                    low = mid + 1;
                }
            }
        }

        System.out.println(liquid[ml] + " " + liquid[mr]);
    }
}