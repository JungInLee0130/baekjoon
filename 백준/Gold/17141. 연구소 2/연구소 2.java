import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, M;
    static int[][] map;
    static ArrayList<Point> arrayList;
    static class Point{
        int r;
        int c;

        int time;

        public Point(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 50이하
        M = Integer.parseInt(st.nextToken()); // 10개 이하

        map = new int[N][N];
        arrayList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    arrayList.add(new Point(i, j, 0));
                }
            }
        }

        len = arrayList.size();
        min = Integer.MAX_VALUE;
        arr = new int[M];
        combination(0, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(min);

        bw.flush();
        bw.close();
        br.close();
    }

    static int len;
    static int min;
    static int[] arr;
    private static void combination(int start, int cnt) {
        if (cnt == M) { // 바이러스의 개수 : 인덱스 0부터
            min = Math.min(min, bfs());
            return;
        }
        for (int i = start; i < len; i++) {
            arr[cnt] = i;
            combination(i + 1, cnt + 1);
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int time = 0;
        for (int i = 0; i < M; i++) {
            Point point = arrayList.get(arr[i]);
            queue.add(point); // 바이러스 포인트 진입
            visited[point.r][point.c] = true;
        }

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            time = poll.time;

            for (int d = 0; d < 4; d++) {
                int nr = poll.r + dr[d];
                int nc = poll.c + dc[d];

                if (!check(nr,nc)) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 1) continue; // 0만 가능.

                visited[nr][nc] = true;
                queue.add(new Point(nr, nc, poll.time + 1));
            }
        }

        // check
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) continue;

                if (!visited[i][j]) {
                    return Integer.MAX_VALUE;
                }
            }
        }

        return time;
    }

    private static boolean check(int nr, int nc) {
        return 0 <= nr && nr <= N - 1 && 0 <= nc && nc <= N - 1;
    }
}