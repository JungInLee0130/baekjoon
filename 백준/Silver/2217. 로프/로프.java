import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // k개 w -> 중량 w/k
        // 물체의 최대 중량
        int[] weight = new int[N]; // 10만개
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(br.readLine());
        }

        // 최소값으로 잡아야할꺼같은데 아니고
        // nCk 에서 Math.min() * k = 최대중량
        // Math.min() *
        // 로프 1개 -> max
        // 로프 2개 -> 2 * 최대 2개 선택 -> min
        // 로프 3개 -> 3 * 최대 3개 선택
        Arrays.sort(weight);

        // 길이 다 다름.
        int max = Integer.MIN_VALUE;
        int idx = N - 1;
        for (int k = 1; k <= N; k++) {
            max = Math.max(max, weight[N - k] * k); // k * weight[idx]
        }
        System.out.println(max);
    }
}