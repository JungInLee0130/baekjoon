import java.awt.*;
import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // -100 ~ 100

        int[][] dp = new int[N][M];
        dp[0][0] = map[0][0];


        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + map[0][j];
        }

        // temp 배열 입갤
        //temp[0][j] : 왼쪽 -> 오른쪽
        //temp[1][j] : 오른쪽 -> 왼쪽
        // 모두 위쪽에서 왔을때랑 비교후
        // 둘중에 큰값을 dp에 넣음.
        // temp는 계속 갱신하면서 써야함.
        int[] leftToRight = new int[M];
        int[] rightToLeft = new int[M];

        for (int i = 1; i < N; i++) {

            // 왼 -> 오
            leftToRight[0] = dp[i - 1][0] + map[i][0];

            for (int j = 1; j <= M - 1; j++) {
                leftToRight[j] = Math.max(leftToRight[j - 1], dp[i - 1][j]) + map[i][j];
            }

            rightToLeft[M - 1] = dp[i - 1][M - 1] + map[i][M - 1];

            for (int j = M - 2; j >= 0; j--) {
                rightToLeft[j] = Math.max(rightToLeft[j + 1], dp[i - 1][j]) + map[i][j];
            }

            for (int j = 0; j <= M - 1; j++) {
                dp[i][j] = Math.max(leftToRight[j], rightToLeft[j]);
            }
        }


        System.out.println(dp[N - 1][M - 1]);

        br.close();
    }
}