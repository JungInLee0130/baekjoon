import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] local;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        local = new int[N];

        for (int i = 0; i < N; i++) {
            local[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        // 일단 상한액을 정하고, 그것보다 작은건 작은대로 배정. 큰거는 상한액 배정

        int high = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += local[i];
            high = Math.max(high, local[i]);
        }

        if (sum <= M) System.out.println(high);
        else{
            high = bs(1, high);
            System.out.println(high);
        }
    }

    private static int getTotal(int high) {
        int total = 0;
        for (int i = 0; i < N; i++) {
            total += Math.min(local[i], high);
        }
        return total;
    }

    private static int bs(int low, int high) {
        while (low < high - 1) {
            int mid = (low + high) / 2;

            if (getTotal(mid) > M) {
                high = mid;
            }
            else{
                low = mid;
            }
        }
        return low;
    }
}