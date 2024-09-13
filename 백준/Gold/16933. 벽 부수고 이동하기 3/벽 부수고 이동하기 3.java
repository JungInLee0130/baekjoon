import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N, M, K;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // 낮과밤
        // 이동할때마다 바뀌고, 이동안하고 가만히있어도 시간지나면 바뀜.
        // 낮에만 벽뿌시기 가능
        System.out.println(bfs());


        bw.flush();
        bw.close();
        br.close();
    }

    static class Point{
        int x;
        int y;

        int cnt;

        int k;

        boolean isDay;

        public Point(int x, int y, int cnt, int k, boolean isDay) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
            this.isDay = isDay;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    private static int bfs() {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(0, 0, 1, K, true));
        boolean[][][] visited = new boolean[N][M][K + 1]; // 1 ~ K
        visited[0][0][K] = true;

        while (!que.isEmpty()) {
            Point poll = que.poll();

            if (poll.x == N - 1 && poll.y == M - 1) {
                return poll.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nx = poll.x + dx[d];
                int ny = poll.y + dy[d];

                if (!isRange(nx, ny)) continue;
                
                if (poll.k > 0 && map[nx][ny] == 1 && !visited[nx][ny][poll.k - 1]) {
                    if (poll.isDay){
                        int nk = poll.k - 1;
                        visited[nx][ny][nk] = true;
                        que.add(new Point(nx, ny, poll.cnt + 1, nk, !poll.isDay)); // 밤으로    
                    }
                    else{
                        que.add(new Point(poll.x, poll.y, poll.cnt + 1, poll.k, !poll.isDay));    
                    }
                }
                else if (map[nx][ny] == 0 && !visited[nx][ny][poll.k]){
                    visited[nx][ny][poll.k] = true;
                    que.add(new Point(nx, ny, poll.cnt + 1, poll.k, !poll.isDay)); // 낮으로
                }
            }
        }
        return -1;
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx <= N - 1 && 0 <= ny && ny <= M - 1;
    }
}