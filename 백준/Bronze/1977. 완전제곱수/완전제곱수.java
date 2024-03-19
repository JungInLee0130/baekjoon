import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine()); // 1만 이하의 자연수

        // 완전제곱수의 합, 최소값
        int min = Integer.MAX_VALUE;
        int sum = 0;

        // 완전제곱수 찾기
        for (int i = 1; i <= 100; i++) {
            int num = i * i;
            if (M <= num && num <= N) {
                sum += num;
                min = Math.min(min, num);
            }
            if (num > N) {
                break;
            }
        }

        if (sum == 0) {
            System.out.println(-1);
        }
        else {
            System.out.println(sum);
            System.out.println(min);
        }
    }
}