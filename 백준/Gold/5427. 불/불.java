import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int T;
    static int R, C;
    static char[][] map;
    static Point start;
    static ArrayList<Point> fires;
    static class Point{
        int x;
        int y;

        int cnt;

        boolean isFire;

        public Point(int x, int y, int cnt, boolean isFire) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.isFire = isFire;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) { // 100
            StringTokenizer st = new StringTokenizer(br.readLine());

            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            map = new char[R][C];
            fires = new ArrayList<>();
            for (int i = 0; i < R; i++) { // 1000
                String str = br.readLine();
                for (int j = 0; j < C; j++) { // 1000
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '@') {
                        start = new Point(i,j, 0, false);
                    }
                    if (map[i][j] == '*') {
                        fires.add(new Point(i, j, 0, true));
                    }
                }
            }

            int min = bfs();

            if (min == -1) {
                bw.write("IMPOSSIBLE\n");
            }
            else{
                bw.write(String.valueOf(min) + "\n");
            }

        }


        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        Queue<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        que.add(start);
        visited[start.x][start.y] = true;

        boolean[][] fireVisited = new boolean[R][C];
        for (Point fire:fires) {
            fireVisited[fire.x][fire.y] = true;
            que.add(fire);
        }

        int personCnt = 0;
        int fireCnt = 0;

        while (!que.isEmpty()) {
            Point poll = que.poll();

            // 상근이일때
            if (!poll.isFire && map[poll.x][poll.y] == '@') {
                for (int i = 0; i < 4; i++) {
                    int nx = poll.x + dx[i];
                    int ny = poll.y + dy[i];

                    if (!check(nx,ny)) return poll.cnt + 1; // 밖이면 clear
                    if (visited[nx][ny]) continue; // 이미 방문한거임
                    if (map[nx][ny] == '.') //빈 공간
                    {
                        map[nx][ny] = '@'; // 이동
                        visited[nx][ny] = true; // 방문
                        que.offer(new Point(nx, ny, poll.cnt + 1, false)); // 넣기
                        personCnt = poll.cnt + 1; // time + 1
                    }
                }
            }

            // 상근 x
            if (poll.isFire && map[poll.x][poll.y] == '*')
            {
                for (int i = 0; i < 4; i++) {
                    int nx = poll.x + dx[i];
                    int ny = poll.y + dy[i];

                    if (!check(nx, ny)) continue; // 밖에 나가면 그냥 아무일없음
                    if (fireVisited[nx][ny]) continue; // 방문

                    // @이나 빈공간이면 ㄱ
                    if (map[nx][ny] == '@' || map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        fireVisited[nx][ny] = true;
                        que.offer(new Point(nx, ny, poll.cnt + 1, true)); // 불임
                        fireCnt = poll.cnt + 1; // time + 1
                    }
                }
            }
            
            if (fireCnt > personCnt) { // fire에 잡아먹힘
                return -1;
            }
        }
        return -1;
    }

    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx <= R - 1 && 0 <= ny && ny <= C - 1;
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
}