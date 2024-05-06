import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int R, C, N;
    static char[][] first;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken()); // N초가 지난후 결과 // 10억초

        first = new char[R][C]; // 0, 1
        char[][] map2 = new char[R][C]; // 2,4,6
        char[][] map3 = new char[R][C]; // 3,7,11
        char[][] map4 = new char[R][C]; // 5,9
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                first[r][c] = str.charAt(c);
                map2[r][c] = 'O';
                map3[r][c] = 'O';
                map4[r][c] = 'O';
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        // 짝수는 모두 설치되어있음
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (first[r][c] == 'O') {
                    map3[r][c] = '.';
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (!check(nr, nc)) {
                            continue;
                        }
                        map3[nr][nc] = '.';
                    }
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map3[r][c] == 'O') {
                    map4[r][c] = '.';
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (!check(nr, nc)) {
                            continue;
                        }
                        map4[nr][nc] = '.';
                    }
                }
            }
        }

        if (N == 0 || N == 1) {
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    System.out.print(first[r][c]);
                }
                System.out.println();
            }
        }

        else if (N % 4 == 1) {
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    System.out.print(map4[r][c]);
                }
                System.out.println();
            }
        } else if (N % 4 == 3) {
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    System.out.print(map3[r][c]);
                }
                System.out.println();
            }
        } else{
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    System.out.print(map2[r][c]);
                }
                System.out.println();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean check(int nr, int nc) {
        return 0 <= nr && nr <= R - 1 && 0 <= nc && nc <= C - 1;
    }
}