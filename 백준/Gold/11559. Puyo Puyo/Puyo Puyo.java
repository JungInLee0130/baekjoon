import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }

        total = 0;
        boolean isDown = true;
        while(isDown) {
            isDown = false;
            visited = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] != '.') {
                        if(bomb(i, j)){
                            isDown = true;
                        }
                    }
                }
            }
            if(isDown){
                down();
                total++;
            }
        }

        bw.write(String.valueOf(total));

        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean bomb(int r, int c) { // 그냥 짜야함.
        isChecked = false;
        char color = map[r][c];
        bfs(r,c, color);
        if (isChecked) {
            return true;
        }
        return false;
    }

    private static void bfs(int r, int c, char color) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r, c));
        boolean[][] visited = new boolean[R][C];
        visited[r][c] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = poll.x + dr[d];
                int nc = poll.y + dc[d];

                if (!check(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (color != map[nr][nc]) continue;

                visited[nr][nc] = true;
                cnt++;
                queue.add(new Point(nr, nc));
            }
        }
        // 4개이상이면 모두 터뜨림
        if (cnt >= 4) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (visited[i][j]) map[i][j] = '.';
                }
            }
            isChecked = true;
        }
    }

    private static void down() {
        for (int c = 0; c < C; c++) {
            // temp에 색깔 순서대로 대입
            ArrayList<Character> temp = new ArrayList<>();
            // 뒤에서부터
            for (int r = R - 1; r >= 0; r--) {
                if (map[r][c] != '.') {
                    temp.add(map[r][c]);
                    map[r][c] = '.';
                }
            }
            int index = 0;
            if (!temp.isEmpty()) {
                // 뒤에서부터 대입
                while (index < temp.size()) {
                    map[R - 1 - index][c] = temp.get(index++);
                }
            }
        }
    }

    private static int total;
    private static boolean[][] visited;
    private static boolean isChecked;
    private static int R = 12;
    private static int C = 6;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};
    
    private static boolean check(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }


}