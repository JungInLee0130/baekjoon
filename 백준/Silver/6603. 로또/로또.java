import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());

            if (k == 0) {
                break;
            }

            int[] arr = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            res = new int[7]; // 0 ~ 5
            combi(arr, k, 0, 0);
            System.out.println();
        }
    }

    static int[] res;
    private static void combi(int[] arr, int k, int idx, int depth) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = idx; i < k; i++) {
            res[depth] = arr[i];
            combi(arr, k, i + 1, depth + 1);
        }
    }
}