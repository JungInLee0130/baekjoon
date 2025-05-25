import java.io.*;
import java.util.*;

class Main {
    static int N, M, K;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        int totalCount = 0;

        if (K > M) {
            System.out.println(totalCount);
            return;
        }

        for (int i = 0; i < N; i++) { // 1000
            int count = 0;
            for (int j = 0; j <= K - 1; j++) { // 10
                if (arr[i][j] == 1) {
                    count += 1;
                }
            }
            if (count == 0) {
                totalCount += 1;
            }

            for (int idx = 1; idx <=  M - K; idx++) { // 1 ~ M - K
                count = count + (arr[i][K - 1 + idx] - arr[i][idx - 1]);

                if (count == 0) {
                    totalCount += 1;
                }
            }
        }

        System.out.println(totalCount);

        br.close();
    }
}