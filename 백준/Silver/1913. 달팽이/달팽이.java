import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        value = Integer.parseInt(br.readLine());

        getSnail();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (value == map[i][j]) {
                    valuePoint = new Point(i + 1, j + 1);
                }
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
        System.out.print(valuePoint.x + " " + valuePoint.y);

        br.close();
    }

    static int N, value;
    static int[][] map;
    static Point valuePoint;
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static void getSnail() {
        map = new int[N][N];

        int count = N * N;

        int[] dx = {1, 0, -1, 0};   // rotate 순서
        int[] dy = {0, 1, 0, -1};

        int d = 0;      // 방향

        int sx = 0;     // 초기 좌표
        int sy = 0;
        map[sx][sy] = count;
        count -= 1;

        while (count > 0) {
            int nx = sx + dx[d];
            int ny = sy + dy[d];

            // 범위가 아니면
            if (!isRange(nx,ny) || encounterValue(nx,ny)) {
                d = (d + 1) % 4;
                continue;
            }

            map[nx][ny] = count;
            count -= 1;

            sx = nx;
            sy = ny;
        }
    }

    private static boolean encounterValue(int nx, int ny) {
        if (map[nx][ny] > 0) {
            return true;
        }
        return false;
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx <= N - 1 && 0 <= ny && ny <= N - 1;
    }

    private static void print() {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}