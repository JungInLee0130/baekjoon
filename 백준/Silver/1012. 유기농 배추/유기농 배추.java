import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int column, row, K;
    private static boolean[][] visited;
    private static int earthWormCount = 0;
    public static void DFS(int[][] area, int x, int y) {
        visited[x][y] = true;

        if (x >= 1) {
            if (area[x - 1][y] == 1 && !visited[x - 1][y]) {
                DFS(area, x - 1, y);
            }
        }
        if (y >= 1) {
            if (area[x][y - 1] == 1 && !visited[x][y - 1]) {
                DFS(area, x, y - 1);
            }
        }

        if (x <= row - 2) {
            if (area[x + 1][y] == 1 && !visited[x + 1][y]) {
                DFS(area, x + 1, y);
            }
        }

        if (y <= column - 2) {
            if (area[x][y + 1] == 1 && !visited[x][y + 1]) {
                DFS(area, x, y + 1);
            }
        }
    }
    public static void earthWorm(int[][] area, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (area[i][j] == 0 || visited[i][j]) {
                    continue;
                } else {
                    DFS(area, i, j);
                    earthWormCount++;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            earthWormCount = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            column = Integer.parseInt(st.nextToken()); // 가로 : 열
            row = Integer.parseInt(st.nextToken()); // 세로 : 행
            K = Integer.parseInt(st.nextToken());

            int[][] area = new int[row][column];

            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    area[j][k] = 0;
                }
            }

            visited = new boolean[row][column];

            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    visited[j][k] = false;
                }
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());

                // 배추의 위치
                int Y = Integer.parseInt(st.nextToken()); // 처음이 가로(Y)
                int X = Integer.parseInt(st.nextToken());

                area[X][Y] = 1;
            }

            // 배추가 인접해있는 땅에 한마리씩 배추흰지렁이를 놓는다.

            earthWorm(area, row, column);


            bw.write(String.valueOf(earthWormCount) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
