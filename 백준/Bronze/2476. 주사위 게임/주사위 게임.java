import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            int three = Integer.parseInt(st.nextToken());

            if (one == two && two == three) {
                int sum = 10000 + (one) * 1000;
                max = Math.max(max, sum);
            } else if (one == two || two == three || three == one) {
                if (one == two) {
                    int sum = 1000 + (one) * 100;
                    max = Math.max(max, sum);
                } else if (one == three) {
                    int sum = 1000 + (one) * 100;
                    max = Math.max(max, sum);
                } else {
                    int sum = 1000 + (two) * 100;
                    max = Math.max(max, sum);
                }
            } else {
                int maxValue = Math.max(Math.max(one, two), three);
                int sum = maxValue * 100;
                max = Math.max(max, sum);
            }
        }

        System.out.println(max);
    }
}