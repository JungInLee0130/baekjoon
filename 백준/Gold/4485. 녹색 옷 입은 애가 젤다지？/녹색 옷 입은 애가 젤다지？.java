import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 잃는 금액을 최소로 함.
        *
        *
        * */
        int t = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            MM = Integer.MAX_VALUE / 1000;
            min = Integer.MAX_VALUE;
            dist = new int[N][N];
            // (0,0) -> (N - 1, N - 1)로 이동.
            disk(0,0);

            bw.write("Problem " + t + ": "+ String.valueOf(min) + "\n");
            t++;
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    private static int min;

    private static int MM;
    private static int[][] dist;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};
    private static void disk(int r, int c) {
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Point(r, c, map[r][c]));
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], MM);
        }
        dist[r][c] = map[r][c];

        while (!priorityQueue.isEmpty()) {
            Point poll = priorityQueue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = poll.x + dr[d];
                int nc = poll.y + dc[d];

                if (!check(nr,nc)) continue;
                if (dist[nr][nc] > dist[poll.x][poll.y] + map[nr][nc]) {
                    dist[nr][nc] = dist[poll.x][poll.y] + map[nr][nc];
                    priorityQueue.add(new Point(nr, nc, dist[nr][nc]));
                }
            }

        }
        min = dist[N - 1][N - 1];
    }

    private static boolean check(int nr, int nc) {
        return 0 <= nr && nr < N && 0 <= nc && nc < N;
    }
}