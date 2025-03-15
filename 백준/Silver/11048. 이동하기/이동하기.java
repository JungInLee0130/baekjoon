import java.io.*;
import java.util.*;

class Main {
    static int M, N;
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

        int[][] dp = new int[N][M];
        dp[0][0] = map[0][0];

        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + map[i][0];
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + map[0][j];
        }

        for (int r = 1; r < N; r++) {
            for (int c = 1; c < M; c++) {
                dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]) + map[r][c];
            }
        }

        System.out.println(dp[N - 1][M - 1]);
        //solve();

        br.close();
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int max = 0;
    private static void solve() {
        int sr = 0;
        int sc = 0;

        int value = map[sr][sc];
        visited = new boolean[N][M];
        //dfs(0, 0, value);
        //bfs(sr,sc, value);

        System.out.println(max);
    }

    static class Point{
        int r;
        int c;
        int v;

        public Point(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
    private static void bfs(int sr, int sc, int value) {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(sr, sc, value));
        visited[sr][sc] = true;

        while (!que.isEmpty()) {
            Point poll = que.poll();

            int r = poll.r;
            int c = poll.c;
            int v = poll.v;

            if (r == N - 1 && c == M - 1) {
                max = Math.max(max, v);
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (isOut(nr,nc)) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                que.add(new Point(nr, nc, v + map[nr][nc]));
            }
        }
    }

    static boolean[][] visited;
    private static void dfs(int r, int c, int value) {
        if (r == N - 1 && c == M - 1){
            max = Math.max(value, max);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isOut(nr,nc)) continue;
            if (visited[nr][nc]) continue;

            visited[nr][nc] = true;
            dfs(nr, nc, value + map[nr][nc]);
            visited[nr][nc] = false;
        }
    }

    private static boolean isOut(int nr, int nc) {
        return 0 > nr || nr > N - 1 || 0 > nc || nc > M - 1;
    }
}