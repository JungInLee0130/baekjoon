import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int N, M;
    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<Point>[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            Point endPoint = new Point(a, b);
            graph[x][y].add(endPoint); // a,b의 열쇠를 가짐.
        }

        switched = new boolean[N][N];
        System.out.println(bfs() + 1);

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited, switched;
    private static int bfs() {
        int count = 0;
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(0,0));

        visited = new boolean[N][N];

        switched[0][0] = true;
        visited[0][0] = true;

        boolean turnOn = false;
        while (!que.isEmpty()) {
            Point poll = que.poll();

            for (Point p : graph[poll.x][poll.y]) {
                int x = p.x;
                int y = p.y;
                if (!switched[x][y]){
                    switched[x][y] = true;
                    count++;
                    turnOn = true;
                }
            };

            for (int d = 0; d < 4; d++) {
                int nx = poll.x + dx[d];
                int ny = poll.y + dy[d];

                if (!isRange(nx,ny)) continue;
                if (!switched[nx][ny] || visited[nx][ny]) continue;

                que.add(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }

        if (turnOn) {
            count += bfs();
        }

        return count;
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx <= N - 1 && 0 <= ny && ny <= N - 1;
    }
}