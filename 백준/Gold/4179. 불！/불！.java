import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static class Point{
        int x;
        int y;

        int count;

        boolean isFire;

        public Point(int x, int y, boolean isFire, int count) {
            this.x = x;
            this.y = y;
            this.isFire = isFire;
            this.count = count;
        }
    }
    static Point jihun;
    static ArrayList<Point> fires;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fires = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    jihun = new Point(i, j, false, 0);
                }
                if (map[i][j] == 'F') {
                    fires.add(new Point(i, j, true, 0));
                }
            }
        }

        boolean isOk = bfs();

        if (isOk) {
            System.out.println(answer);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }

    private static boolean bfs() {
        // fire 먼저
        Queue<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        for (Point fire:fires) {
            visited[fire.x][fire.y] = true;
            que.add(fire);
        }
        que.add(jihun);
        visited[jihun.x][jihun.y] = true;

        while (!que.isEmpty()) {
            Point poll = que.poll();

            boolean isFire = poll.isFire;
            int curTime = poll.count;

            // 불이면
            if (isFire) {
                for (int d = 0; d < 4; d++) {
                    int nx = poll.x + dx[d];
                    int ny = poll.y + dy[d];

                    if (!isRange(nx,ny)) continue;
                    // 방문했으면
                    if (visited[nx][ny]) continue;

                    // 벽이면
                    if (map[nx][ny] == '#') continue;

                    visited[nx][ny] = true;
                    que.add(new Point(nx, ny, true, curTime + 1));
                }
            }
            // 지훈이면
            else{
                for (int d = 0; d < 4; d++) {
                    int nx = poll.x + dx[d];
                    int ny = poll.y + dy[d];

                    // 탈출가능
                    if (!isRange(nx,ny)){
                        answer = curTime + 1;
                        return true;
                    }
                    if (visited[nx][ny]) continue;

                    if (map[nx][ny] == '#') continue;

                    visited[nx][ny] = true;
                    que.add(new Point(nx, ny, false, curTime + 1));
                }
            }
        }

        return false;
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx <= R - 1
                && 0 <= ny && ny <= C - 1;
    }
    static int answer = -1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
}