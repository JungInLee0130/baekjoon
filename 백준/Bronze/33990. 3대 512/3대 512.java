import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int total = -1;
        int sub = 512;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = new int[3];
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                A[j] = Integer.parseInt(st.nextToken());
                sum += A[j];
            }

            if (Math.abs(512 - sum) < sub && sum >= 512) {
                total = sum;
                sub = Math.abs(512 - sum);
            }
        }

        System.out.println(total);

        br.close();
    }
}