import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] map;
    static int[][] fan;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        // -1 : 공기청정기
        fan = new int[2][2]; // 위, 아래
        int k = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1) {
                    fan[k][0] = i;
                    fan[k][1] = j;
                    k++;
                }
            }
        }
        
        // 미세먼지가 퍼지고 -> 공기청정기 작동
        while (T > 0) {
            spread();
            move();
            T--;
            //print();
        }

        int sum = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    sum += map[i][j];
                }
            }
        }

        System.out.println(sum);

        bw.flush();
        br.close();
        bw.close();
    }

    private static void print() {
        System.out.println("-----------" + T + "-------------");
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 공기청정기 방향으로 순환
    private static void move() {
        // 그냥 카피 배열 쓰는게 낫지않을까
        after = new int[R][C];

        int x1 = fan[0][0];
        int x2 = fan[1][0];

        // 위쪽 반시계
        // (0,0)(x1,0) : x + 1
        for (int r = 0; r < x1; r++) {
            after[r + 1][0] = map[r][0];
        }
        // (x1, 0)(x1, C - 1) : y + 1
        for (int c = 0; c < C - 1; c++) {
            after[x1][c + 1] = map[x1][c];
        }
        // (x1, C - 1)(0,C - 1) : x - 1
        for (int r = x1; r > 0; r--) {
            after[r - 1][C - 1] = map[r][C - 1];
        }
        // (0, 0)(0, C - 1) : y - 1
        for (int c = C - 1; c >= 1; c--) {
            after[0][c - 1] = map[0][c];
        }
        // 아래쪽 시계
        // (x2,0)(R-1,0) : x - 1
        for (int r = x2 + 1; r <= R - 1; r++) {
            after[r - 1][0] = map[r][0];
        }
        // (R - 1,0)(R - 1, C - 1) : y - 1
        for (int c = 1; c <= C - 1; c++) {
            after[R - 1][c - 1] = map[R - 1][c];
        }
        // (R - 1, C - 1) (x2, C - 1) : x + 1
        for (int r = x2; r <= R - 2; r++) {
            after[r + 1][C - 1] = map[r][C - 1];
        }
        // (x2, C - 1) (x2, 0) : y + 1
        for (int c = 0; c <= C - 2; c++) {
            after[x2][c + 1] = map[x2][c];
        }

        // -1인곳 -> 0
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (after[i][j] == -1) {
                    after[i][j] = 0;
                }
            }
        }

        // 환풍기 초기화
        after[fan[0][0]][fan[0][1]] = -1;
        after[fan[1][0]][fan[1][1]] = -1;

        // 복사
        for (int r = 0; r < R; r++) {
            map[r][0] = after[r][0];
            map[r][C - 1] = after[r][C - 1];
        }
        for (int c = 0; c < C; c++) {
            map[0][c] = after[0][c];
            map[R - 1][c] = after[R - 1][c];
            map[x1][c] = after[x1][c];
            map[x2][c] = after[x2][c];
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    private static void spread() {
        after = new int[R][C]; // 1. 초기화

        for (int i = 0; i < R; i++) { // 2. 입력
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) { // 확산
                    dust(i,j);
                }
            }
        }

        // 3. 복사
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = after[i][j];
            }
        }
    }

    static int[][] after;
    private static void dust(int x, int y) {
        int num = map[x][y] / 5;
        after[x][y] += map[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dr[i];
            int ny = y + dc[i];

            // 범위 벗어나면
            if (0 > nx || nx > R - 1 || 0 > ny || ny > C - 1) continue;
            // 공기 청정기가 있으면
            if (map[nx][ny] == -1) continue;
            after[x][y] -= num;
            after[nx][ny] += num; // 복사배열에 입력
        }
    }
}