import java.io.*;
import java.util.*;

class Main {
    static int N, M, K;
    static int[] money;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        money = new int[N];


        int max = 0;
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(br.readLine());
            max = Math.max(money[i], max);
        }

        int low = max;
        int high = 10000 * 100000;

        int answer = 10000 * 100000;

        while (low <= high) {
            int mid = (low + high) / 2;

            int left = mid;

            int count = 1;

            for (int i = 0; i < N; i++) {
                int m = money[i];

                if (left < m) {
                    left = mid;
                    count++; // 인출횟수 증가
                }

                left -= m;
            }

            if (count <= M) {
                answer = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        System.out.println(answer);


        br.close();
    }
}