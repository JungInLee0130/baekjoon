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
        // 더이상 터질께 없을때
        boolean isDown = true;
        while(isDown) {
            isDown = false;
            visited = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] != '.') {
                        // 터뜨리기 : 못터뜨리면 다음.
                        if(bomb(i, j)){
                            isDown = true;
                            // 출력
                        }
                    }
                }
            }
            // 떨어뜨리기
            if(isDown){
                down();
                total++;
                /*System.out.println();
                for (int r = 0; r < 12; r++) {
                    for (int c = 0; c < 6; c++) {
                        System.out.print(map[r][c]);
                    }
                    System.out.println();
                }
                System.out.println();
                // 출력*/
            }
        }

        bw.write(String.valueOf(total));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void down() {
        for (int c = 0; c < C; c++) {
            ArrayList<Character> temp = new ArrayList<>();
            for (int r = R - 1; r >= 0; r--) {
                if (map[r][c] != '.') {
                    temp.add(map[r][c]);
                    map[r][c] = '.';
                }
            }
            int index = 0;
            if (!temp.isEmpty()) {
                while (index < temp.size()) {
                    map[R - 1 - index][c] = temp.get(index++);
                }
            }
        }
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
        if (cnt >= 4) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (visited[i][j]) map[i][j] = '.';
                }
            }
            isChecked = true;
        }
    }

    private static int total;
    private static boolean[][] visited;
    private static boolean isChecked;
    private static void dfs(int r, int c, int cnt, char color) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!check(nr,nc)) continue;
            if (visited[nr][nc]) continue;
            if (color != map[nr][nc]) continue;

            visited[nr][nc] = true;
            if (cnt >= 3) {
                isChecked = true;
                dfs(nr, nc, cnt + 1, color);
            }
            else {
                dfs(nr, nc, cnt + 1, color);
            }
        }
        if (isChecked) {
            map[r][c] = '.';
        }
    }

    private static int R = 12;
    private static int C = 6;
    // 근처에 4개있으면 제거후 아래로 떨어뜨림.
    // 그리고 아마 위에부터 제거해야할거임.
    // 위에 부터 훑는다.
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};
    
    // 한번 다 터진다음에 -> 내려오고 -> 또 다터짐.

    private static boolean check(int r, int c)
    {
        return 0 <= r && r < R && 0 <= c && c < C;
    }


}
/* 터질때마다 1연쇄 증가
* 상하좌우 4개이상 같은 색이 있을 경우 터짐.
* 여러그룹이 터져도 1연쇄임.
* 12x6,
* RGBPY
* .은 빈공간
* 몇 연쇄, 하나도 안일어나면 0출력
* */