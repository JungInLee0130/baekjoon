import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited; // map, red, blue
    static Hole hole; // 구명 좌표
    static Marble red, blue;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1}; // 상우하좌
    static class Marble{
        int rx;
        int ry;
        int bx;
        int by;
        int cnt;

        public Marble(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }
    static class Hole{
        int x;
        int y;

        public Hole(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'B') {
                    blue = new Marble(0, 0, i, j, 0);
                }
                if (map[i][j] == 'R') {
                    red = new Marble(i, j, 0, 0, 0);
                }
                if (map[i][j] == 'O') {
                    hole = new Hole(i, j);
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Marble> queue = new LinkedList<>();
        queue.add(new Marble(red.rx, red.ry, blue.bx, blue.by, 1));
        visited[red.rx][red.ry][blue.bx][blue.by] = true;

        while (!queue.isEmpty()) {
            Marble poll = queue.poll();

            int curRr = poll.rx;
            int curRc = poll.ry;
            int curBr = poll.bx;
            int curBc = poll.by;
            int curCnt = poll.cnt;

            if (curCnt > 10) {
                return -1;
            }

            for (int d = 0; d < 4; d++) {
                int newRr = curRr;
                int newRc = curRc;
                int newBr = curBr;
                int newBc = curBc;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 한꺼번에 이동
                // 빨간 구슬 이동
                while (map[newRr + dr[d]][newRc + dc[d]] != '#'){
                    newRr += dr[d];
                    newRc += dc[d];

                    if (newRr == hole.x && newRc == hole.y){
                        isRedHole = true;
                        break;
                    }
                }

                // 파란 구슬 이동
                while (map[newBr + dr[d]][newBc + dc[d]] != '#') {
                    newBr += dr[d];
                    newBc += dc[d];

                    if (newBr == hole.x && newBc == hole.y) {
                        isBlueHole = true;
                        break;
                    }
                }

                // 파란공 빠짐 : 걸러냄
                if (isBlueHole) continue;
                // 파란공 안빠지고, 빨간공이 빠짐. -> 최소횟수 리턴
                if (isRedHole) return curCnt;

                // 둘다 구멍에 빠지지않고 : 위치가 같은경우 ->
                if (newRr == newBr && newRc == newBc) {
                    if (d == 0) { // 위쪽으로 기울이기 : 더 큰쪽이 뒤
                        if (curRr > curBr) newRr -= dr[d];
                        else newBr -= dr[d];
                    } else if (d == 1) { // 오른쪽으로 기울이기 : 더 작은쪽이 뒤
                        if (curRc < curBc) newRc -= dc[d];
                        else newBc -= dc[d];
                    } else if (d == 2) { // 아래쪽 : 더 작은게 뒤
                        if (curRr < curBr) newRr -= dr[d];
                        else newBr -= dr[d]; // 틀이 짜여진뒤에는 오타 싸움.
                    } else{ // 왼쪽 : 더 큰게 뒤
                        if (curRc > curBc) newRc -= dc[d];
                        else newBc -= dc[d];
                    }
                }

                // 이미 방문한 경우
                if (visited[newRr][newRc][newBr][newBc]) {
                    continue;
                }

                // 새로운 경로
                visited[newRr][newRc][newBr][newBc] = true;
                queue.add(new Marble(newRr, newRc, newBr, newBc, curCnt + 1));
            }
        }
        return -1;
    }
}