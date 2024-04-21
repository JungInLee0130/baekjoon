import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 1000
        M = Integer.parseInt(st.nextToken()); // 1000

        // 1000000
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // U L R D
        isSafe = new boolean[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isSafe[i][j]) {
                    dfs(i, j);
                }
            }
        }

        System.out.println(count);


        bw.flush();
        bw.close();
        br.close();
    }

    static boolean[][] isSafe;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int[][] dp;
    static int count;

    private static void dfs(int x, int y) {
        if (dp[x][y] >= 1) {
            return;
        }
        if (visited[x][y]) {
            isSafe[x][y] = true;
            count++;
            return;
        }

        visited[x][y] = true;

        int index = move(map[x][y]);

        int nx = x + dr[index];
        int ny = y + dc[index];

        if (!check(nx,ny)) return;

        dfs(nx, ny);

        dp[x][y] = count;
    }

    private static int move(char c) { // index
        if (c == 'U') {
            return 0;
        }
        if (c == 'D') {
            return 1;
        }
        if (c == 'L') {
            return 3;
        }
        if (c == 'R') {
            return 2;
        }
        return -1; // none; // safezone
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited = new boolean[N][M];
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dr[i];
                int ny = poll[1] + dc[i];

                if (!check(nx,ny)) continue;
                if (visited[nx][ny]) continue;
            }
        }
    }

    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx <= N - 1 && 0 <= ny && ny <= M - 1;
    }
}