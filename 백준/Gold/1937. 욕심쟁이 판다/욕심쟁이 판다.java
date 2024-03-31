import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // <= 500

        // 판다는 전 지역보다 많은 대나무가 있는 곳으로 이동한다.
        // 어떤 지점에 처음 풀어놔야 가장 많이 이동할지

        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.println(max);
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!check(nx,ny)) continue;
            if (map[x][y] < map[nx][ny]){
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
                //dfs(nx, ny);
            }
        }

        return dp[x][y];
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point{
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    private static int bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y, 1));

        Point poll = new Point(x, y, 1);
        dp[x][y] = 1;
        while (!queue.isEmpty()) {
            poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (!check(nx, ny)) continue;
                if (map[nx][ny] <= map[poll.x][poll.y]) continue;

                if (dp[nx][ny] > 0) { // 최대치가 담겨있음
                    dp[x][y] = poll.cnt + dp[nx][ny];
                    continue;
                }

                queue.add(new Point(nx, ny, poll.cnt + 1));
            }
        }

        if (dp[x][y] < poll.cnt) {
            dp[x][y] = poll.cnt;
        }

        return dp[x][y];
    }

    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < N;
    }
}