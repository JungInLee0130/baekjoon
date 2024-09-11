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

        // 0 : ㄱㅊ, 1 : 벽
        // K개 까지 뿌술수있음
        boolean isPossible = bfs();

        if (isPossible) {
            System.out.println(min);
        }
        else{
            System.out.println(-1);
        }


        bw.flush();
        bw.close();
        br.close();
    }
    static class Point{
        int x;
        int y;
        int cnt;

        int breakCount;

        public Point(int x, int y, int cnt, int breakCount) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.breakCount = breakCount;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int min = Integer.MAX_VALUE;
    private static boolean bfs() {
        // K개까지됨.
        // K개 아래도 가능
        // 이것도 그냥 우선순위큐?
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(0, 0, 1, 0));
        boolean[][][] visited = new boolean[N][M][K + 1];
        visited[0][0][0] = true;

        while (!que.isEmpty()) {
            Point poll = que.poll();

            int cnt = poll.cnt;
            int breakCount = poll.breakCount;

            if (poll.x == N - 1 && poll.y == M - 1) {
                min = poll.cnt;
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int nx = poll.x + dx[d];
                int ny = poll.y + dy[d];

                // 범위 밖임
                if (!isRange(nx,ny)) continue;

                // 여기서는 방문여부를 갈라야함.
                // 다시 되돌아가면 안되게
                // 벽임 : 뚫을수있음
                if (map[nx][ny] == 1) {
                    if (breakCount < K){
                        if (!visited[nx][ny][breakCount + 1]){
                            int nBreakCount = breakCount + 1;
                            visited[nx][ny][nBreakCount] = true;
                            que.add(new Point(nx, ny, cnt + 1, nBreakCount));
                        }
                    }
                }
                else {
                    if (map[nx][ny] == 0) {
                        if (!visited[nx][ny][breakCount]){
                            // 그냥 방임 : 그냥 갈수있음
                            visited[nx][ny][breakCount] = true;
                            que.add(new Point(nx, ny, cnt + 1, breakCount));// 이동은 해야
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx <= N - 1 && 0 <= ny && ny <= M - 1;
    }
}