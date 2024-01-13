import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 육지마다 bfs 돌려서 시간이 가장 오래걸리는 곳을 출력하면됨.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') { // 육지일때 bfs.
                    maxHour = Math.max(maxHour, bfs(i,j));
                }
            }
        }

        System.out.println(maxHour);

        bw.flush();
        br.close();
        bw.close();
    }

    static int maxHour = 0;
    static Queue<int[]> queue;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    private static int bfs(int x, int y) {
        visited = new boolean[R][C];
        queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        visited[x][y] = true;

        int hour = 0;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            hour = poll[2];

            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dr[i];
                int ny = poll[1] + dc[i];

                if (0 > nx || nx > R - 1 || 0 > ny || ny > C - 1) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 'W') continue;
                
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny, poll[2] + 1}); // 새로운 지점 대입
                // 동시에 퍼져야함.
            }
        }

        return hour;
    }

}