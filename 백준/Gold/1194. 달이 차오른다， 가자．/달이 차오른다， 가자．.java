import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, M;
    static char[][] map;
    static Point start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        start = new Point();
        // 그냥 미로 탈출하는데 걸리는 최소값 구하기
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') {
                    start = new Point(i, j, 0, 0);
                }
            }
        }

        // 가는데 최단거리에 필요한 열쇠들
        // 열쇠들 찾고 -> 가는데 시간 vs 그냥 돌아서 가는시간 -> 사실 그건 별 중요하지않음. 따로 생각할 필요가없다.
        System.out.println(bfs());
        
        

        bw.flush();
        bw.close();
        br.close();
    }

    static boolean[][][] visited;
    static class Point{
        int x;
        int y;
        int cnt;
        int key;

        public Point() {
        }

        public Point(int x, int y, int cnt, int key) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.key = key;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited = new boolean[64][N][M]; // 열쇠 비트마스킹
        visited[0][start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            int key = poll.key; // 현재 갖고있는 key

            if (map[poll.x][poll.y] == '1') {
                return poll.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nx = poll.x + dx[d];
                int ny = poll.y + dy[d];

                if (!check(nx,ny)) continue; // 범위 아님
                if (map[nx][ny] == '#') continue; // 벽임
                if (visited[key][nx][ny]) continue; // key들고있는 현상태에서 이미 방문함.

                // 열쇠방문
                if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f'){
                    // 새로운 key 비트마스킹
                    // 6 5 4 3 2 1 순
                    int newKey = 1 << (map[nx][ny] - 'a');
                    newKey = newKey | key; // 기존키랑 합하고
                    if (!visited[newKey][nx][ny]) {
                        visited[key][nx][ny] = true;
                        visited[newKey][nx][ny] = true;
                        queue.offer(new Point(nx, ny, poll.cnt + 1, newKey));
                    }
                    continue;
                }
                // 방 방문
                else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    int door = 1 << (map[nx][ny] - 'A');
                    if ((key & door) > 0) { // 있으면
                        visited[key][nx][ny] = true;
                        queue.offer(new Point(nx, ny, poll.cnt + 1, key));
                    }
                    continue;
                }
                // 나머지 칸의 경우
                visited[key][nx][ny] = true;
                queue.offer(new Point(nx, ny, poll.cnt + 1, key)); // 횟수 늘려줌.
            }
        }
        return -1;
    }

    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < M;
    }


}