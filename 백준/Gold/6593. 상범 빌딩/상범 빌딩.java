import java.io.*;
import java.util.*;

public class Main {
    static int L, R, C;
    static class Point{
        int x;
        int y;

        int z;

        int count;

        public Point(int x, int y, int z, int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
        }
    }
    static Point start, end;
    static char[][][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            map = new char[R][C][L];

            start = new Point(0, 0, 0, 0);
            end = new Point(0, 0, 0, 0);
            for (int k = 0; k < L; k++) {
                for (int i = 0; i < R; i++) {
                    String str = br.readLine();
                    for (int j = 0; j < C; j++) {
                        map[i][j][k] = str.charAt(j);
                        if (map[i][j][k] == 'S') {
                            start = new Point(i, j, k, 0);
                        }
                        if (map[i][j][k] == 'E') {
                            end = new Point(i, j, k, 0);
                        }
                    }
                }
                String blank = br.readLine(); // 그냥 공백
            }

            int answer = bfs();

            if (answer == -1) {
                sb.append("Trapped!\n");
            } else {
                sb.append("Escaped in " + answer + " minute(s).\n");
            }
        }
        System.out.println(sb);
    }

    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    private static int bfs() {
        Queue<Point> que = new LinkedList<>();
        que.add(start);
        boolean[][][] visited = new boolean[R][C][L];
        visited[start.x][start.y][start.z] = true;

        while (!que.isEmpty()) {
            Point poll = que.poll();

            int curTime = poll.count;

            if (map[poll.x][poll.y][poll.z] == 'E') {
                return curTime;
            }

            // 동서남북상하
            for (int d = 0; d < 6; d++) {
                int nx = poll.x + dx[d];
                int ny = poll.y + dy[d];
                int nz = poll.z + dz[d];

                if (!isRange(nx,ny,nz)) continue;
                if (visited[nx][ny][nz]) continue;

                // 다른 조건
                // 1. 금 임
                if (map[nx][ny][nz] == '#') continue;

                visited[nx][ny][nz] = true;
                que.add(new Point(nx, ny, nz, curTime + 1));
            }
        }
        return -1;
    }

    private static boolean isRange(int nx, int ny, int nz) {
        return 0 <= nx && nx <= R - 1
                && 0 <= ny && ny <= C - 1
                && 0 <= nz && nz <= L - 1;
    }
}