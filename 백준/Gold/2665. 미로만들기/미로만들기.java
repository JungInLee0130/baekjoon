import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static int[][] map;
    // 우선순위 큐 사용 ㅁㅊ
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                int num = str.charAt(j) - '0';
                map[i][j] = num;
            }
        }

        bfs();

        System.out.println(min);


        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, 0)); // (0,0) 시작 (n-1,n-1) 끝
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Point poll = pq.poll();

            if (poll.x == N - 1 && poll.y == N - 1) {
                min = poll.blackCount;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = poll.x + dx[d];
                int ny = poll.y + dy[d];
                int nc = poll.blackCount;

                if (!isRange(nx, ny)) continue;

                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;

                // 0 : 검은방
                if (map[nx][ny] == 0) {
                    pq.add(new Point(nx, ny, nc + 1));
                }
                else{
                    pq.add(new Point(nx, ny, nc));
                }
            }
        }
    }

    private static boolean[][] visited;


    static class Point implements Comparable<Point>{
        int x;
        int y;

        int blackCount;

        public Point(int x, int y, int blackCount) {
            this.x = x;
            this.y = y;
            this.blackCount = blackCount;
        }

        @Override
        public int compareTo(Point o) {
            return this.blackCount - o.blackCount;
        }
    }

    static int min = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx <= N - 1 && 0 <= ny && ny <= N - 1;
    }
}