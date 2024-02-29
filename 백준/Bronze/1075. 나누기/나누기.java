import java.io.*;
import java.util.*;

public class Main {
    static int N, F;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        F = Integer.parseInt(br.readLine());

        int one = N % 10;

        int ten = N / 10 % 10;

        // (ten, one) = (0,0) ~ (9,9)

        arr = new int[2];
        isTrue = false;
        dfs(N - ten * 10 - one, 0);

        if (0 <= answer && answer <= 9) {
            StringBuilder sb = new StringBuilder();
            sb.append(0);
            sb.append(answer);
            System.out.println(sb.toString());
        }
        else {
            System.out.println(answer);
        }
    }

    static int[] arr;
    static boolean isTrue;
    static int answer;
    private static void dfs(int num, int depth) {
        if (isTrue) return;

        if (depth == 2) {
            if (num % F == 0) {
                answer = num % 100;
                isTrue = true;
                return;
            }
            else{
                return;
            }
        }

        for (int i = 0; i <= 9; i++) {
            if (!isTrue) {
                arr[depth] = i;
                dfs(num + i * (int) Math.pow(10, 1 - depth), depth + 1);
            }
        }
    }
}