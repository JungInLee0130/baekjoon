import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int[][] map;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N][M];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));

        bw.flush();
        br.close();
        bw.close();
    }

    static int[] dr = {-1,1, 0, 0}; // 아래, 오른쪽, 왼쪽
    static int[] dc = {0,0, 1, -1};
    
    // dp는 경우의 수 저장 용도
    private static int dfs(int x, int y) {
        // 도착지점까지 도달했을 경우
        if (x == N-1 && y == M-1) return 1;

        // 방문한 적이 없는 경우
        if (dp[x][y] == -1) {
            dp[x][y] = 0;
            for (int i=0; i<4; i++) {
                int nx = x + dr[i];
                int ny = y + dc[i];

                if (nx < 0 || nx > N-1 || ny < 0 || ny > M-1) continue;

                // 내리막 길인 경우
                if (map[x][y] > map[nx][ny]) {
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }
        return dp[x][y];
    }
}