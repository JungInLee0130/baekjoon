import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /*
        *
        *
        * */
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken()); // 가로
        R = Integer.parseInt(st.nextToken()); // 세로

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        visited = new boolean[R][C];
        dist = new int[R][C];
        min = Integer.MAX_VALUE;
        dijkstra(0,0);

        bw.write(String.valueOf(min));


        bw.flush();
        br.close();
        bw.close();
    }

    private static int[][] dist;
    private static int MM = Integer.MAX_VALUE / 1000;
    private static void dijkstra(int r, int c) {
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Point(r, c, 0));
        for (int i = 0; i < R; i++) {
            Arrays.fill(dist[i], MM);
        }
        dist[r][c] = 0;

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
        min = dist[R - 1][C - 1];
    }

    private static boolean[][] visited;
    private static int min;

    private static class Point implements Comparable<Point>{
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
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};


    private static boolean check(int nr, int nc) {
        return 0 <= nr && nr < R && 0 <= nc && nc < C;
    }
}